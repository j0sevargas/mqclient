package net.baccredomatic.ibmmqwebclient.service;

import javax.jms.Connection;
import javax.jms.JMSException;

import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.jms.MQQueue;
import com.ibm.msg.client.wmq.WMQConstants;
import com.ibm.msg.client.wmq.compat.jms.internal.JMSC;

import org.springframework.stereotype.Service;

import net.baccredomatic.ibmmqwebclient.domain.Message;

@Service
public class IBMMQMessageService implements MessageService {

    @Override
    public Message send(Message message) throws Exception {

        MQConnectionFactory factory = null;

        Connection connection = null;

        Session insession = null;
        Session outsession = null;

        MQQueue outQueue = null;
        MQQueue responseQueue = null;

        MessageProducer messageProducer = null;
        MessageConsumer messageConsumer = null;

        TextMessage theActualMessage = null;
        javax.jms.Message responseMessage = null;

        long time = System.currentTimeMillis();

        boolean sendOnly = (message.getReplyTo() == null || message.getReplyTo().trim().isEmpty());

        try {
            factory = getQCF(message);
            connection = factory.createConnection();
            connection.start();

            outsession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            outQueue = new MQQueue(message.getToQueue());
            outQueue.setTargetClient(JMSC.MQJMS_CLIENT_NONJMS_MQ);
            messageProducer = outsession.createProducer(outQueue);
            theActualMessage = outsession.createTextMessage();
            theActualMessage.setText(message.getMessage());
            if (!sendOnly) {
                responseQueue = new MQQueue(message.getReplyTo());
                theActualMessage.setJMSReplyTo(responseQueue);
            }

            messageProducer.send(theActualMessage, javax.jms.Message.DEFAULT_DELIVERY_MODE,
                    javax.jms.Message.DEFAULT_PRIORITY, javax.jms.Message.DEFAULT_TIME_TO_LIVE);

            message.setId(theActualMessage.getJMSMessageID());

            if (!sendOnly) {

                insession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                messageConsumer = insession.createConsumer(responseQueue,
                        "JMSCorrelationID = '" + theActualMessage.getJMSMessageID() + "'");

                responseMessage = messageConsumer.receive(message.getTimeout());

                if (responseMessage == null) {

                    message.setMessage("El mensaje de respuesta es nulo! Timeout");

                } else {

                    message.setId(responseMessage.getJMSMessageID());
                    message.setCorrelId(responseMessage.getJMSCorrelationID());
                    if (responseMessage instanceof TextMessage){
                        message.setMessage(((TextMessage) responseMessage).getText());
                    }  else {
                        message.setMessage(responseMessage.toString());
                    }
                }

            }

        } catch (Exception e) {

            message.setMessage(e + "  " + e.getMessage());

        } finally {
            if (messageProducer != null) {
                messageProducer.close();
            }
            if (outsession != null) {
                outsession.close();
            }
            if (insession != null) {
                insession.close();
            }
            if (messageConsumer != null) {
                messageConsumer.close();
            }
        }

        time = System.currentTimeMillis() - time;
        
        message.setTotalTime(time);


        return message;

    }

    private MQConnectionFactory getQCF(Message message) throws JMSException {

        MQConnectionFactory factory = new MQConnectionFactory();
        factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
        factory.setHostName(message.getHost());
        factory.setPort(message.getPort());
        factory.setQueueManager(message.getQueueManager());
        factory.setChannel(message.getChannel());

        return factory;
    }

}
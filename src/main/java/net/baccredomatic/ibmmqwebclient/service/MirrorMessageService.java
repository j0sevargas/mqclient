package net.baccredomatic.ibmmqwebclient.service;


import net.baccredomatic.ibmmqwebclient.domain.Message;


public class MirrorMessageService implements MessageService{

    @Override
    public Message send(Message message) throws Exception{
        return message;
    }

    @Override
    public Message get(Message message) throws Exception {
        return message;
    }

}
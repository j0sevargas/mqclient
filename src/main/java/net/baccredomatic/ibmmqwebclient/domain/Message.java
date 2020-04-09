package net.baccredomatic.ibmmqwebclient.domain;

public class Message {

    private String toQueue;
    private String replyTo;
    private String message;
    private String queueManager;
    private String host;
    private Integer port;
    private Integer timeout;
    private String channel;
    private String id;
    private String correlId;
    private Long totalTime;

    public String getToQueue() {
        return toQueue;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public void setToQueue(String toQueue) {
        this.toQueue = toQueue;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getQueueManager() {
        return queueManager;
    }

    public void setQueueManager(String queueManager) {
        this.queueManager = queueManager;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Message(String toQueue, String replyTo, String message, String queueManager, String host, Integer port,
            String channel, Integer timeout) {
        this.toQueue = toQueue;
        this.replyTo = replyTo;
        this.message = message;
        this.queueManager = queueManager;
        this.host = host;
        this.port = port;
        this.channel = channel;
        this.timeout = timeout;
    }

    public Message() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorrelId() {
        return correlId;
    }

    public void setCorrelId(String correlId) {
        this.correlId = correlId;
    }

    public Message(String message) {
        this.message = message;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    
}
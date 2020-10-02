package net.baccredomatic.ibmmqwebclient.service;

import net.baccredomatic.ibmmqwebclient.domain.Message;

public interface MessageService {

    public Message send(Message message) throws Exception;

    public Message get(Message message) throws Exception;

}
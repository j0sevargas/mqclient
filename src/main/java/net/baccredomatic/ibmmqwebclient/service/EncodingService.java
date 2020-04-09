package net.baccredomatic.ibmmqwebclient.service;

import net.baccredomatic.ibmmqwebclient.domain.Message;

public interface EncodingService {

    public Message encode (Message message);
    public Message decode (Message message);
}
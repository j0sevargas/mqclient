package net.baccredomatic.ibmmqwebclient.service;

import java.util.Base64;

import org.springframework.stereotype.Service;

import net.baccredomatic.ibmmqwebclient.domain.Message;

@Service
public class Base64EncoderService implements EncodingService {

    @Override
    public Message encode(Message message) {
        String encoded = Base64.getEncoder().encodeToString(message.getMessage().getBytes());
        message.setMessage(encoded);
        return message;
    }

    @Override
    public Message decode(Message message) {
        String decoded = new String(Base64.getDecoder().decode(message.getMessage()));
        message.setMessage(decoded);
        return message;
    }

}
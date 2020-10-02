package net.baccredomatic.ibmmqwebclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.baccredomatic.ibmmqwebclient.domain.Message;
import net.baccredomatic.ibmmqwebclient.service.MessageService;
import net.baccredomatic.ibmmqwebclient.service.EncodingService;

@RestController
public class MessageController {

    
    private MessageService service;

    @Autowired
    public void setService(MessageService service) {
        this.service = service;
    }

    private EncodingService encoder;

    @Autowired
    public void setEncoder(EncodingService encoder) {
        this.encoder = encoder;
    }


    
    @PostMapping(path = "/message")
    public ResponseEntity<Message> send(@RequestBody Message message) {

        ResponseEntity<Message> response;

        try {

            response = new ResponseEntity<>(encoder.encode(service.send(encoder.decode(message))), HttpStatus.OK);

        } catch (Exception e) {

            response = new ResponseEntity<>(new Message(e + " " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }

    @PostMapping(path = "/get")
    public ResponseEntity<Message> getMessage(@RequestBody Message message) {

        ResponseEntity<Message> response;
        
        try {
            // No hace decode porque no se espera una peticion del usuario. Solo encode de lo obtenido
            response = new ResponseEntity<>(encoder.encode(service.get(message)), HttpStatus.OK);

        } catch (Exception e) {

            response = new ResponseEntity<>(new Message(e + " " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
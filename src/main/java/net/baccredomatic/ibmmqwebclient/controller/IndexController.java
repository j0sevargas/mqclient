package net.baccredomatic.ibmmqwebclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/message")
    public String getIndex(){
        return "index.html";
    }

}
package com.provider.controller;

import com.provider.sender.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "provider")
public class ProviderController {

    @Autowired
    private SendMessage sendMessage;

    @RequestMapping(value = "/sendMessage")
    public String sendMessage(@RequestParam(value = "json") String json){
        sendMessage.sendTestMessgae(json);
        return "hello World";
    }

}

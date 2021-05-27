package com.provider.controller;

import com.provider.sender.SendMessage;
import com.server.pojo.MainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/receiveMessage")
    public String receiveMessage(@RequestParam(value = "json") String mainInfo){
        return sendMessage.receiveMessage(mainInfo);
    }


    @RequestMapping(value = "/xdlmessage")
    public String xdlmessage(@RequestParam(value = "param") String param){
        return sendMessage.xdlmessage(param);
    }

    @RequestMapping(value = "/sendEsStr")
    public String sendEsStr(@RequestParam(value = "str") String str){
        return sendMessage.sendEsStr(str);
    }
}

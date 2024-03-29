package com.provider.sender;

import com.server.pojo.MainInfo;
import com.server.utils.GetUuid;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class SendMessage {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String s) {
            System.out.println("correlationData   " + correlationData);
            System.out.println("ack   " + ack);
            // ack == false 表示发送失败，可以进行其他补偿操作
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.out.println("returnCallback");
        }
    };

    public void sendTestMessgae(String msg){
        rabbitTemplate.setConfirmCallback(confirmCallback);
/*        rabbitTemplate.addBeforePublishPostProcessors(new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.out.println("addBeforePublishPostProcessors");
                String str = null;
                try {
                    str = new String(message.getBody(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String str1 = str + "d";
                Message message1 = MessageBuilder.withBody(str1.getBytes(StandardCharsets.UTF_8)).build();
                return  message1;
            }
        });*/
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData("123");
        Message message = MessageBuilder
                .withBody(msg.getBytes(StandardCharsets.UTF_8))
                .build();
        rabbitTemplate.convertAndSend("exchange-1","springboot.test", message, correlationData);
    }


    public String receiveMessage(String mainInfo) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        String yzm = GetUuid.getUuid();
        CorrelationData correlationData = new CorrelationData(yzm);
        Message message = MessageBuilder
                .withBody(mainInfo.getBytes(StandardCharsets.UTF_8))
                .build();
        rabbitTemplate.convertAndSend("exchange-1","email.yzm", message, correlationData);
        return yzm;
    }


    public String xdlmessage(String params){
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        String yzm = GetUuid.getUuid();
        CorrelationData correlationData = new CorrelationData(yzm);
        String param = params + yzm;
        Message message = MessageBuilder
                .withBody(param.getBytes(StandardCharsets.UTF_8))
                .setExpiration("20000")
                .build();
        rabbitTemplate.convertAndSend("no_change","no.test", message, correlationData);
        return param;
    }

    public String sendEsStr(String str) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        String uuid = GetUuid.getUuid();
        CorrelationData correlationData = new CorrelationData(uuid);
        Message message = MessageBuilder
                .withBody(str.getBytes(StandardCharsets.UTF_8))
                .build();
        rabbitTemplate.convertAndSend("es-exchange","es.log", message, correlationData);
        return "OK";
    }
}

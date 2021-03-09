package com.consumer.acceptmessage;

import com.consumer.utils.SendAliEmail;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.server.pojo.MainInfo;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

@Component
public class AcceptMessage {


    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            value = "queue-1", durable = "true"
                    ),
                    exchange = @Exchange(
                            value = "exchange-1",
                            durable = "true",
                            type = "topic",
                            ignoreDeclarationExceptions = "true"
                    ),
                    key = "springboot.*"
            )
    )
    @RabbitHandler
    public void onMessage(Channel channel,
                          @Headers Map<String,Object> headers,
                          Message message) throws IOException {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String payload = new String((byte[]) message.getPayload(),"UTF-8");
        System.out.println(payload);
        channel.basicQos(0,1, false);
        channel.basicAck(deliveryTag,false);
    }


    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            value = "queue-2", durable = "true"
                    ),
                    exchange = @Exchange(
                            value = "exchange-1",
                            durable = "true",
                            type = "topic",
                            ignoreDeclarationExceptions = "true"
                    ),
                    key = "springboot.#"
            )
    )
    @RabbitHandler
    public void onMessageSecond(Channel channel,
                          @Headers Map<String,Object> headers,
                          Message message) throws IOException {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String payload = new String((byte[]) message.getPayload(),"UTF-8");
        System.out.println(payload);
        channel.basicQos(0,1, false);
        channel.basicAck(deliveryTag,false);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            value = "queue-3", durable = "true"
                    ),
                    exchange = @Exchange(
                            value = "exchange-1",
                            durable = "true",
                            type = "topic",
                            ignoreDeclarationExceptions = "true"
                    ),
                    key = "email.*"
            )
    )
    @RabbitHandler
    public void receiveMessage(Channel channel,
                                @Headers Map<String,Object> headers,
                                Message message) throws IOException {
        try {
            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            //String payload = new String((byte[]) message.getPayload(),"UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            MainInfo mainInfo = objectMapper.readValue((byte[])message.getPayload(), MainInfo.class);
            SendAliEmail.sendMail(mainInfo);
            channel.basicQos(0,1, false);
            channel.basicAck(deliveryTag,false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

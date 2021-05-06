package com.consumer.createchannel;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CreateChannel {


    private final static String QUEUE = "no_consumer";

    private final static String EXCHANGE = "no_change";

    private final static String ROUTINGKEY = "no.*";


    @Bean
    public Exchange directExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue queue(){
        Map<String, Object> map = new HashMap<>(2);
        map.put("x-dead-letter-exchange","xdl_exchange");  //声明死信交换机
        map.put("x-dead-letter-routing-key","xdl.test"); //声明死信路由键
        return QueueBuilder.durable(QUEUE).withArguments(map).build();
    }

    @Bean
    public Binding binding(){
        return new Binding(QUEUE, Binding.DestinationType.QUEUE, EXCHANGE, ROUTINGKEY, null);
    }


}

package com.example.consumer.consumer.exchange;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder
                .directExchange("ipi.event")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }
}

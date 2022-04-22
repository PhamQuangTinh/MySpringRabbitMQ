package com.example.consumer.consumer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "ipi.anhtin.ipi-order", durable = "true"),
                    exchange = @Exchange(value = "ipi.anhtin", ignoreDeclarationExceptions = "true"),
                    key = "orderRoutingKey"
            )
    })
    public void doOrderConsumer(){

    }
}

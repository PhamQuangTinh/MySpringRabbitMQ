package com.example.consumer.consumer.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventQueue {

    @Bean
    public Queue accumulatedEventQue(){
        return QueueBuilder
                .durable("ipi.event-valid.accumulated.event-event.service")
                .autoDelete()
                .quorum()
                .lazy()
                .exclusive()
                .ttl(6000)
                .expires(100000)
                .leaderLocator(QueueBuilder.LeaderLocator.minLeaders)
                .overflow(QueueBuilder.Overflow.rejectPublish)
                .deadLetterExchange("dlx")
                .deadLetterRoutingKey("dlrk")
                .maxLength(50)
                .maxPriority(4)
                .singleActiveConsumer()
                .build();
    }
}

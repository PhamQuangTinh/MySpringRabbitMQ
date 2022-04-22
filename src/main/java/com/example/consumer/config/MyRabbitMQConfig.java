package com.example.consumer.config;


import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRabbitMQConfig {

    Logger logger = LoggerFactory.getLogger(MyRabbitMQConfig.class);

    @Bean(name = "ConnectionFactory")
    public ConnectionFactory connectionFactory(
            @Value("${spring.rabbitmq.host}") String host,
            @Value("${spring.rabbitmq.port}") int port,
            @Value("${spring.rabbitmq.username}") String username,
            @Value("${spring.rabbitmq.password}") String password,
            @Value("${spring.rabbitmq.virtual-host}") String virtualHost,
            @Value("${spring.rabbitmq.publisher-returns}") boolean publisherReturns,
            @Value("${spring.rabbitmq.publisher-confirm-type}") CachingConnectionFactory.ConfirmType confirmType
    ) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherReturns(publisherReturns);
        connectionFactory.setPublisherConfirmType(confirmType);
        connectionFactory.setConnectionTimeout(5000);
        // Số connection tối đa được kết nối 1 lần
        connectionFactory.setConnectionLimit(8);
        // Số connection tối đa trong chế độ chờ
        connectionFactory.setConnectionCacheSize(10);
        // Số channel trong 1 connection
        connectionFactory.setChannelCacheSize(5);
        // Thời gian để tạo kết nối với 1 channel, nếu vượt 10000 milis, thrown AmqpTimeoutException
        connectionFactory.setChannelCheckoutTimeout(10000);

        connectionFactory.addConnectionListener(new ConnectionListener() {
            @Override
            public void onCreate(Connection connection) {

            }

            @Override
            public void onClose(Connection connection) {
            }

            @Override
            public void onShutDown(ShutdownSignalException signal) {
            }

            @Override
            public void onFailed(Exception exception) {
            }
        });
        return connectionFactory;
    }

    @Bean(name = "RabbitTemplate")
    public RabbitTemplate listenerContainerFactory(@Qualifier("ConnectionFactory") ConnectionFactory connectionFactory,
                                                                         @Value("${spring.rabbitmq.template.mandatory}") Boolean mandatory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(mandatory);
        rabbitTemplate.setConfirmCallback((correlationData, ack, s) -> {
            if (!ack) {
                logger.info("Message {} failed to send RabbitMQ message ack confirmation, because {}", correlationData.getId(), s);
            } else {
                logger.info("Success to send RabbitMQ message ack confirmation");
            }
        });
        rabbitTemplate.setReturnsCallback((returnedMessage) -> {
            logger.error("Fail to execute the message, Exchange:{}, Routing Key: {}, Message: {}, Reply Text: {}",returnedMessage.getExchange(), returnedMessage.getRoutingKey(), returnedMessage.getMessage(), returnedMessage.getReplyText());
        });
        return rabbitTemplate;
    }


    @Bean(name = "ContainerFactory")
    public SimpleRabbitListenerContainerFactory hospSyncFactory(
            @Qualifier("ConnectionFactory") ConnectionFactory connectionFactory,
            @Value("${spring.rabbitmq.listener.simple.acknowledge-mode}") String acknowledge,
            @Value("${spring.rabbitmq.listener.simple.prefetch}") Integer prefetch
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.valueOf(acknowledge.toUpperCase()));
        factory.setPrefetchCount(prefetch);
        return factory;
    }

    @Bean(name = "RabbitAdmin")
    public RabbitAdmin rabbitAdmin(
            @Qualifier("ConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
}

package com.troojer.msnotification.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConfiguration {
    @Bean
    public Queue getEmailQueue() {
        return new Queue("emailMessageQueue");
    }

    @Bean
    public Queue getSmsQueue() {
        return new Queue("smsMessageQueue");
    }

    @Bean
    public Queue getNotificationQueue() {
        return new Queue("innerNotificationQueue");
    }
}

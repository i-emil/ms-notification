package com.troojer.msnotification.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.troojer.msnotification.model.EmailMessageDto;
import com.troojer.msnotification.service.EmailMessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final EmailMessageService emailMessageService;
    private final ObjectMapper objectMapper;

    public MessageListener(EmailMessageService emailMessageService, ObjectMapper objectMapper) {
        this.emailMessageService = emailMessageService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "emailMessageQueue")
    public void listenEmailQueue(String messageStr) throws JsonProcessingException {
        EmailMessageDto message = objectMapper.readValue(messageStr, EmailMessageDto.class);
        emailMessageService.addMessage(message);
    }

    @RabbitListener(queues = "smsQueue")
    public void listenSmsQueue(String messageStr) throws JsonProcessingException {
        EmailMessageDto message = objectMapper.readValue(messageStr, EmailMessageDto.class);
        emailMessageService.addMessage(message);
    }

}

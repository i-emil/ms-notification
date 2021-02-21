package com.troojer.msnotification.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.troojer.msnotification.model.EmailMessageDto;
import com.troojer.msnotification.model.InnerNotificationDto;
import com.troojer.msnotification.model.SmsDto;
import com.troojer.msnotification.service.EmailMessageService;
import com.troojer.msnotification.service.InnerNotificationService;
import com.troojer.msnotification.service.SmsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final EmailMessageService emailMessageService;
    private final SmsService smsService;
    private final InnerNotificationService innerNotificationService;
    private final ObjectMapper objectMapper;

    public MessageListener(EmailMessageService emailMessageService, SmsService smsService, InnerNotificationService innerNotificationService, ObjectMapper objectMapper) {
        this.emailMessageService = emailMessageService;
        this.smsService = smsService;
        this.innerNotificationService = innerNotificationService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "emailMessageQueue")
    public void listenEmailQueue(String messageStr) throws JsonProcessingException {
        EmailMessageDto message = objectMapper.readValue(messageStr, EmailMessageDto.class);
        emailMessageService.addMessage(message);
    }

    @RabbitListener(queues = "smsMessageQueue")
    public void listenSmsQueue(String messageStr) throws JsonProcessingException {
        SmsDto message = objectMapper.readValue(messageStr, SmsDto.class);
        smsService.addAndSendMessage(message);
    }

    @RabbitListener(queues = "innerNotificationQueue")
    public void listenInnerNotificationQueue(String messageStr) throws JsonProcessingException {
        InnerNotificationDto message = objectMapper.readValue(messageStr, InnerNotificationDto.class);
        innerNotificationService.addNotification(message);
    }

}

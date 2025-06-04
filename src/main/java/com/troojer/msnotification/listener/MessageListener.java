package com.troojer.msnotification.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.troojer.msnotification.mapper.NotificationMapper;
import com.troojer.msnotification.model.EmailMessageDto;
import com.troojer.msnotification.model.NotificationDto;
import com.troojer.msnotification.model.SmsDto;
import com.troojer.msnotification.service.EmailMessageService;
import com.troojer.msnotification.service.NotificationService;
import com.troojer.msnotification.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final EmailMessageService emailMessageService;
    private final SmsService smsService;
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;
    private final NotificationMapper notificationMapper;

    @RabbitListener(queues = "#{rabbitMqConfiguration.getEmailQueue()}")
    public void listenEmailQueue(String messageStr) throws JsonProcessingException {
        EmailMessageDto message = objectMapper.readValue(messageStr, EmailMessageDto.class);
        emailMessageService.addMessage(message);
    }

    @RabbitListener(queues = "#{rabbitMqConfiguration.getSmsQueue()}")
    public void listenSmsQueue(String messageStr) throws JsonProcessingException {
        SmsDto message = objectMapper.readValue(messageStr, SmsDto.class);
        smsService.addAndSendMessage(message);
    }

    @RabbitListener(queues = "#{rabbitMqConfiguration.getNotificationQueue()}")
    public void listenInnerNotificationQueue(String messageStr) throws JsonProcessingException {
        NotificationDto message = objectMapper.readValue(messageStr, NotificationDto.class);
        notificationService.pushNotification(notificationMapper.dtoToNotificationRequest(message));
    }

}

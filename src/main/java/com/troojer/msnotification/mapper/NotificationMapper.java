package com.troojer.msnotification.mapper;

import com.troojer.msnotification.model.NotificationDto;
import com.troojer.msnotification.model.PushNotificationRequest;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public PushNotificationRequest dtoToNotificationRequest(NotificationDto notificationDto) {
        return PushNotificationRequest.builder()
                .to(notificationDto.getRecipientsId())
                .notification(PushNotificationRequest.Notification.builder().title(notificationDto.getTitle()).body(notificationDto.getDescription()).build())
                .data(PushNotificationRequest.Data.builder().build())
                .build();

    }

}

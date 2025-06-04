package com.troojer.msnotification.service.impl;

import ch.qos.logback.classic.Logger;
import com.troojer.msnotification.client.FirebaseNotificationClient;
import com.troojer.msnotification.model.PushNotificationRequest;
import com.troojer.msnotification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

    private final FirebaseNotificationClient firebaseNotificationClient;

    @Override
    public void pushNotification(PushNotificationRequest pushNotification) {
        firebaseNotificationClient.push(pushNotification);
    }

}

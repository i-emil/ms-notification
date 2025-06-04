package com.troojer.msnotification.service;


import com.troojer.msnotification.model.PushNotificationRequest;

public interface NotificationService {

    void pushNotification(PushNotificationRequest pushNotification);

}

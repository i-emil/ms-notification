package com.troojer.msnotification.service;


import com.troojer.msnotification.model.InnerNotificationDto;
import com.troojer.msnotification.model.InnerNotificationStatus;
import com.troojer.msnotification.model.SendingStatus;
import com.troojer.msnotification.model.SmsDto;

import java.util.List;
import java.util.Map;

public interface InnerNotificationService {
    void addNotification(InnerNotificationDto innerNotificationDto);

    List<InnerNotificationDto> getNotifications();

    void setNotificationStatus(Long id, InnerNotificationStatus status);
}

package com.troojer.msnotification.service;


import com.troojer.msnotification.model.NotificationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {
    void addNotification(NotificationDto notificationDto);

    List<NotificationDto> getNotifications(Pageable pageable);

}

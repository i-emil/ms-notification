package com.troojer.msnotification.controller;

import com.troojer.msnotification.model.NotificationDto;
import com.troojer.msnotification.service.NotificationService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<NotificationDto> getUserNotifications(Pageable pageable) {
        return notificationService.getNotifications(pageable);
    }

}

package com.troojer.msnotification.controller;

import com.troojer.msnotification.model.InnerNotificationDto;
import com.troojer.msnotification.model.InnerNotificationStatus;
import com.troojer.msnotification.service.InnerNotificationService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.troojer.msnotification.model.InnerNotificationStatus.READ;

@RestController
@RequestMapping("notifications")
public class InnerNotificationController {

    private final InnerNotificationService innerNotificationService;

    public InnerNotificationController(InnerNotificationService innerNotificationService) {
        this.innerNotificationService = innerNotificationService;
    }

    @GetMapping
    public List<InnerNotificationDto> getUserNotifications(Pageable pageable) {
        return innerNotificationService.getNotifications(pageable);
    }

    @PutMapping("read/{notificationId}")
    public void readNotification(@PathVariable Long notificationId) {
        innerNotificationService.setNotificationStatus(notificationId, READ);
    }

}

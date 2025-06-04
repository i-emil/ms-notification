package com.troojer.msnotification;

import com.troojer.msnotification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MsNotificationApplication {

    private final NotificationService notificationService;

    public static void main(String[] args) {
        SpringApplication.run(MsNotificationApplication.class, args);
    }

}

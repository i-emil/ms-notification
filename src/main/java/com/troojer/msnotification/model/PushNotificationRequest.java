package com.troojer.msnotification.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.troojer.msnotification.model.enm.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PushNotificationRequest {

    @Size(min = 1, max = 1000)
    @JsonProperty(value = "registration_ids")
    private List<String> to;
    private Notification notification;
    private Data data;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Notification {
        private final String sound = "default";
        private final String priority = "high";
        @JsonProperty("content_available")
        private final boolean contentAvailable = true;
        private String title;
        private String body;
        private String image;
        private String icon;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Data {
        private int id;
        private String smallImage;
        private String largeImage;
        private NotificationType type;
    }
}

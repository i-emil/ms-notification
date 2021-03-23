package com.troojer.msnotification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.troojer.msnotification.model.enm.NotificationStatus;
import com.troojer.msnotification.model.enm.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NotificationDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private Map<String, String> params;

    @Pattern(regexp = "INFO|EVENT_CHANGE|EVENT_RECOMMEND")
    private NotificationType type;

    @NotBlank
    private String userId;

    @JsonProperty(access = READ_ONLY)
    private NotificationStatus status;

}
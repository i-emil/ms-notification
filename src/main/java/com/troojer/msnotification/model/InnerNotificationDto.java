package com.troojer.msnotification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.troojer.msnotification.constraints.DateParameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.troojer.msnotification.model.SendingStatus.PENDING;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InnerNotificationDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    private String title;

    private String description;

    private InnerNotificationType type;

    private String userId;

    private LocalDateTime sendingDate;

    @JsonProperty(access = READ_ONLY)
    private InnerNotificationStatus status;

}
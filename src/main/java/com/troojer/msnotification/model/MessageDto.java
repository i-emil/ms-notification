package com.troojer.msnotification.model;

import com.troojer.msnotification.model.enm.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageDto {

    private List<String> usersId;

    private NotificationType type;

    private String title;

    private String message;
}

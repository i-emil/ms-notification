package com.troojer.msnotification.mapper;

import com.troojer.msnotification.dao.EmailMessageEntity;
import com.troojer.msnotification.model.EmailMessageDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailMessageMapper {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public EmailMessageEntity dtoToEntity(EmailMessageDto dto) {
        return EmailMessageEntity.builder()
                .userId(dto.getUserId())
                .email(dto.getEmail())
                .subject(dto.getSubject())
                .messageText(dto.getMessageText())
                .sendingDate((dto.getSendingDate() != null) ? LocalDateTime.parse(dto.getSendingDate(), formatter) : null)
                .build();
    }

    public EmailMessageDto entityToDto(EmailMessageEntity entity) {
        return EmailMessageDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .email(entity.getEmail())
                .subject(entity.getSubject())
                .messageText(entity.getMessageText())
                .sendingDate((entity.getSendingDate() != null) ? entity.getSendingDate().format(formatter) : null)
                .build();
    }

    public List<EmailMessageDto> entitiesToDtos(Collection<EmailMessageEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
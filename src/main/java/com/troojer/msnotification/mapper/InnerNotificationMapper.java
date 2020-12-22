package com.troojer.msnotification.mapper;

import com.troojer.msnotification.dao.InnerNotificationEntity;
import com.troojer.msnotification.model.InnerNotificationDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InnerNotificationMapper {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public InnerNotificationEntity dtoToEntity(InnerNotificationDto dto) {
        return InnerNotificationEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .type(dto.getType())
                .userId(dto.getUserId())
                .sendingDate((dto.getSendingDate() != null) ? LocalDateTime.parse(dto.getSendingDate(), formatter) : null)
                .build();
    }

    public InnerNotificationDto entityToDto(InnerNotificationEntity entity) {
        return InnerNotificationDto.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .type(entity.getType())
                .userId(entity.getUserId())
                .sendingDate((entity.getSendingDate() != null) ? entity.getSendingDate().format(formatter) : null)
                .build();
    }

    public List<InnerNotificationDto> entitiesToDtos(Collection<InnerNotificationEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}

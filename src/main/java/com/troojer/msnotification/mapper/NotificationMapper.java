package com.troojer.msnotification.mapper;

import com.troojer.msnotification.dao.NotificationEntity;
import com.troojer.msnotification.model.NotificationDto;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationMapper {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public NotificationEntity dtoToEntity(NotificationDto dto) {
        return NotificationEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .type(dto.getType())
                .userId(dto.getUserId())
                .params(dto.getParams())
                .build();
    }

    public NotificationDto entityToDto(NotificationEntity entity) {
        return NotificationDto.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .type(entity.getType())
                .userId(entity.getUserId())
                .params(entity.getParams())
                .build();
    }

    public List<NotificationDto> entitiesToDtos(Collection<NotificationEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}

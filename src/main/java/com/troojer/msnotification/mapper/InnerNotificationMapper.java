package com.troojer.msnotification.mapper;

import com.troojer.msnotification.dao.InnerNotificationEntity;
import com.troojer.msnotification.model.InnerNotificationDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InnerNotificationMapper {

    public InnerNotificationEntity dtoToEntity(InnerNotificationDto dto) {
        return InnerNotificationEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .type(dto.getType())
                .userId(dto.getUserId())
                .sendingDate(dto.getSendingDate())
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
                .sendingDate(entity.getSendingDate())
                .build();
    }

    public List<InnerNotificationDto> entitiesToDtos(Collection<InnerNotificationEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}

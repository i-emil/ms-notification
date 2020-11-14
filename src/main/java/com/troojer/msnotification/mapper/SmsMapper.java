package com.troojer.msnotification.mapper;

import com.troojer.msnotification.dao.SmsEntity;
import com.troojer.msnotification.model.SmsDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SmsMapper {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public SmsEntity dtoToEntity(SmsDto dto) {
        return SmsEntity.builder()
                .userId(dto.getUserId())
                .phoneNumber(dto.getPhoneNumber())
                .region("az")
                .messageText(dto.getMessageText())
                .sendingDate((dto.getSendingDate() != null) ? LocalDateTime.parse(dto.getSendingDate(), formatter) : null)
                .build();
    }

    public SmsDto entityToDto(SmsEntity entity) {
        return SmsDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .phoneNumber(entity.getPhoneNumber())
                .region(entity.getRegion())
                .messageText(entity.getMessageText())
                .sendingDate((entity.getSendingDate() != null) ? entity.getSendingDate().format(formatter) : null)
                .build();
    }

    public List<SmsDto> entitiesToDtos(Collection<SmsEntity> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
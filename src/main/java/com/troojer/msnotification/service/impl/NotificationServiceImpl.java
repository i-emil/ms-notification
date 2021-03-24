package com.troojer.msnotification.service.impl;

import ch.qos.logback.classic.Logger;
import com.troojer.msnotification.client.MessageClient;
import com.troojer.msnotification.dao.NotificationEntity;
import com.troojer.msnotification.dao.respository.InnerNotificationRepository;
import com.troojer.msnotification.mapper.NotificationMapper;
import com.troojer.msnotification.model.MessageDto;
import com.troojer.msnotification.model.NotificationDto;
import com.troojer.msnotification.model.enm.MessageType;
import com.troojer.msnotification.service.NotificationService;
import com.troojer.msnotification.util.AccessCheckerUtil;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationServiceImpl implements NotificationService {

    private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

    private final InnerNotificationRepository innerNotificationRepository;
    private final NotificationMapper notificationMapper;
    private final AccessCheckerUtil accessChecker;
    private final MessageClient messageClient;

    public NotificationServiceImpl(InnerNotificationRepository innerNotificationRepository, NotificationMapper notificationMapper, AccessCheckerUtil accessChecker, MessageClient messageClient) {
        this.innerNotificationRepository = innerNotificationRepository;
        this.notificationMapper = notificationMapper;
        this.accessChecker = accessChecker;
        this.messageClient = messageClient;
    }

    @Override
    public void addNotification(NotificationDto dto) {
        dto.getRecipientsId().forEach(userId ->
                innerNotificationRepository.save(notificationMapper.dtoToEntity(dto, userId))
        );
        messageClient.addMessage(MessageDto.builder().type(dto.getType()).title(dto.getTitle()).message(dto.getDescription()).usersId(dto.getRecipientsId()).build());
        logger.info("addNotification(); dto: {}", dto);
    }

    @Override
    public List<NotificationDto> getNotifications(Pageable pageable) {
        List<NotificationEntity> notifications = innerNotificationRepository.findAllByUserId(accessChecker.getUserId(), pageable);
        return notificationMapper.entitiesToDtos(notifications);
    }


}

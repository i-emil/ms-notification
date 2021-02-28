package com.troojer.msnotification.service.impl;

import ch.qos.logback.classic.Logger;
import com.troojer.msnotification.dao.InnerNotificationEntity;
import com.troojer.msnotification.dao.respository.InnerNotificationRepository;
import com.troojer.msnotification.mapper.InnerNotificationMapper;
import com.troojer.msnotification.model.InnerNotificationDto;
import com.troojer.msnotification.model.InnerNotificationStatus;
import com.troojer.msnotification.model.exception.NotFoundException;
import com.troojer.msnotification.service.InnerNotificationService;
import com.troojer.msnotification.util.AccessCheckerUtil;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.troojer.msnotification.model.InnerNotificationStatus.NEW;
import static com.troojer.msnotification.model.InnerNotificationStatus.SENT;

@Component
public class InnerNotificationServiceImpl implements InnerNotificationService {

    private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

    private final InnerNotificationRepository innerNotificationRepository;
    private final InnerNotificationMapper innerNotificationMapper;
    private final AccessCheckerUtil accessChecker;

    public InnerNotificationServiceImpl(InnerNotificationRepository innerNotificationRepository, InnerNotificationMapper innerNotificationMapper, AccessCheckerUtil accessChecker) {
        this.innerNotificationRepository = innerNotificationRepository;
        this.innerNotificationMapper = innerNotificationMapper;
        this.accessChecker = accessChecker;
    }

    @Override
    public void addNotification(InnerNotificationDto dto) {
        innerNotificationRepository.save(innerNotificationMapper.dtoToEntity(dto));
        logger.info("addNotification(); dto: {}", dto);
    }

    @Override
    public List<InnerNotificationDto> getNotifications(Pageable pageable) {
        List<InnerNotificationEntity> notifications = innerNotificationRepository.findAllByUserId(accessChecker.getUserId(), pageable);
        List<InnerNotificationEntity> newNotifications = notifications.stream().filter(x -> x.getStatus() == NEW).collect(Collectors.toList());
        newNotifications.forEach(x -> x.setStatus(SENT));
        innerNotificationRepository.saveAll(newNotifications);
        return innerNotificationMapper.entitiesToDtos(notifications);
    }

    @Override
    public void setNotificationStatus(Long id, InnerNotificationStatus status) {
        InnerNotificationEntity innerNotification = innerNotificationRepository.findById(id).orElseThrow(() -> {
            logger.warn("setNotificationStatus(); notfound; id: {}", id);
            return new NotFoundException("notification not exist");
        });
        innerNotification.setStatus(status);
        innerNotificationRepository.save(innerNotification);
        logger.info("setNotificationStatus(); id: {}, status: {}", id, status);
    }


}

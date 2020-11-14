package com.troojer.msnotification.service.impl;

import ch.qos.logback.classic.Logger;
import com.troojer.msnotification.dao.SmsEntity;
import com.troojer.msnotification.dao.respository.SmsRepository;
import com.troojer.msnotification.mapper.SmsMapper;
import com.troojer.msnotification.model.SendingStatus;
import com.troojer.msnotification.model.SmsDto;
import com.troojer.msnotification.service.SmsService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.troojer.msnotification.model.SendingStatus.IN_PROCESS;

@Service
public class SmsServiceImpl implements SmsService {

    private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

    private final SmsRepository smsRepository;
    private final SmsMapper smsMapper;

    public SmsServiceImpl(SmsRepository smsRepository, SmsMapper smsMapper) {
        this.smsRepository = smsRepository;
        this.smsMapper = smsMapper;
    }

    @Override
    public void addMessage(SmsDto message) {
        smsRepository.save(smsMapper.dtoToEntity(message));
    }

    @Override
    public List<SmsDto> getMessages() {
        List<SmsEntity> messages = smsRepository.getMessagesToSend(LocalDateTime.now().plusMinutes(5));
        messages.forEach(m -> m.setStatus(IN_PROCESS));
        smsRepository.saveAll(messages);
        logger.info("getMessages(); setStatus inProcess, ids: {}", messages.stream().map(SmsEntity::getId).toArray());
        return smsMapper.entitiesToDtos(messages);
    }

    @Override
    public void setSendStatus(Map<Long, SendingStatus> messagesStatus) {
        List<SmsEntity> messages = smsRepository.findAllById(messagesStatus.keySet());
        messages.forEach(m -> m.setStatus(messagesStatus.get(m.getId())));
        smsRepository.saveAll(messages);
        logger.info("setSendStatus(); ids: {}", messagesStatus);
    }
}
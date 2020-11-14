package com.troojer.msnotification.service.impl;

import ch.qos.logback.classic.Logger;
import com.troojer.msnotification.dao.EmailMessageEntity;
import com.troojer.msnotification.dao.respository.EmailMessageRepository;
import com.troojer.msnotification.mapper.EmailMessageMapper;
import com.troojer.msnotification.model.EmailMessageDto;
import com.troojer.msnotification.model.SendingStatus;
import com.troojer.msnotification.service.EmailMessageService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.troojer.msnotification.model.SendingStatus.IN_PROCESS;

@Service
public class EmailMessageServiceImpl implements EmailMessageService {

    private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

    private final EmailMessageRepository emailMessageRepository;
    private final EmailMessageMapper messageMapper;

    public EmailMessageServiceImpl(EmailMessageRepository emailMessageRepository, EmailMessageMapper messageMapper) {
        this.emailMessageRepository = emailMessageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public void addMessage(EmailMessageDto message) {
        emailMessageRepository.save(messageMapper.dtoToEntity(message));
    }

    @Override
    public List<EmailMessageDto> getMessages() {
        List<EmailMessageEntity> messages = emailMessageRepository.getMessagesToSend(LocalDateTime.now().plusMinutes(5));
        messages.forEach(m -> m.setStatus(IN_PROCESS));
        emailMessageRepository.saveAll(messages);
        logger.info("getMessages(); setStatus inProcess, ids: {}", messages.stream().map(EmailMessageEntity::getId).toArray());
        return messageMapper.entitiesToDtos(messages);
    }

    @Override
    public void setSendStatus(Map<Long, SendingStatus> messagesStatus) {
        List<EmailMessageEntity> messages = emailMessageRepository.findAllById(messagesStatus.keySet());
        messages.forEach(m -> m.setStatus(messagesStatus.get(m.getId())));
        emailMessageRepository.saveAll(messages);
        logger.info("setSendStatus(); ids: {}", messagesStatus);
    }
}
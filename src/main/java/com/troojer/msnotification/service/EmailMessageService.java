package com.troojer.msnotification.service;


import com.troojer.msnotification.model.EmailMessageDto;
import com.troojer.msnotification.model.SendingStatus;

import java.util.List;
import java.util.Map;

public interface EmailMessageService {
    void addMessage(EmailMessageDto message);

    List<EmailMessageDto> getMessages();

    void setSendStatus(Map<Long, SendingStatus> messagesStatus);
}

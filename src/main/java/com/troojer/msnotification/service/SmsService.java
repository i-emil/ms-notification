package com.troojer.msnotification.service;


import com.troojer.msnotification.model.EmailMessageDto;
import com.troojer.msnotification.model.SendingStatus;
import com.troojer.msnotification.model.SmsDto;

import java.util.List;
import java.util.Map;

public interface SmsService {
    void addAndSendMessage(SmsDto message);

    List<SmsDto> getMessagesToSend();

    void setSendingStatus(Map<Long, SendingStatus> messagesStatus);
}

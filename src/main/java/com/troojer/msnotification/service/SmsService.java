package com.troojer.msnotification.service;


import com.troojer.msnotification.model.enm.SendingStatus;
import com.troojer.msnotification.model.SmsDto;

import java.util.List;
import java.util.Map;

public interface SmsService {
    void addAndSendMessage(SmsDto message);

    List<SmsDto> getMessagesToSend();

    void setSendingStatus(Map<Long, SendingStatus> messagesStatus);
}

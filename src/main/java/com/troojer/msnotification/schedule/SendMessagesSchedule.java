package com.troojer.msnotification.schedule;

import ch.qos.logback.classic.Logger;
import com.troojer.msnotification.client.MailJetEmailSenderClient;
import com.troojer.msnotification.client.SoftlineSmsSenderClient;
import com.troojer.msnotification.model.EmailMessageDto;
import com.troojer.msnotification.model.SendingStatus;
import com.troojer.msnotification.model.SmsDto;
import com.troojer.msnotification.service.EmailMessageService;
import com.troojer.msnotification.service.SmsService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SendMessagesSchedule {

    private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

    private final EmailMessageService emailMessageService;
    private final SmsService smsService;

    private final MailJetEmailSenderClient mailJetEmailSenderClient;

    private final SoftlineSmsSenderClient softlineSmsSenderClient;

    public SendMessagesSchedule(EmailMessageService emailMessageService, SmsService smsService, MailJetEmailSenderClient mailJetEmailSenderClient, SoftlineSmsSenderClient softlineSmsSenderClient) {
        this.emailMessageService = emailMessageService;
        this.smsService = smsService;
        this.mailJetEmailSenderClient = mailJetEmailSenderClient;
        this.softlineSmsSenderClient = softlineSmsSenderClient;
    }

    @Scheduled(fixedDelay = 60000L)
    @SchedulerLock(name = "sendEmailMessages")
    public void sendEmails() {
        List<EmailMessageDto> messages = emailMessageService.getMessages();
        Map<Long, SendingStatus> messageMap = mailJetEmailSenderClient.sendEmails(messages);
        emailMessageService.setSendStatus(messageMap);
        logger.info("sendEmails(): {}", messageMap);
    }

    @Scheduled(fixedDelay = 60000L)
    @SchedulerLock(name = "sendSms")
    public void sendSms() {
        //todo activate in prod
//        List<SmsDto> messages = smsService.getMessages();
//        Map<Long, SendingStatus> messageMap = softlineSmsSenderClient.sendAllSms(messages);
//        smsService.setSendStatus(messageMap);
//        logger.info("sendSms(): {}", messageMap);
    }
}

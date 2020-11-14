package com.troojer.msnotification.controller;

import com.troojer.msnotification.model.EmailMessageDto;
import com.troojer.msnotification.model.SmsDto;
import com.troojer.msnotification.service.EmailMessageService;
import com.troojer.msnotification.service.SmsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("messages")
public class MessageController {
    private final EmailMessageService emailMessageService;
    private final SmsService smsService;

    public MessageController(EmailMessageService emailMessageService, SmsService smsService) {
        this.emailMessageService = emailMessageService;
        this.smsService = smsService;
    }

    @PostMapping("mail")
    public void addMailMessages(@RequestBody @Valid EmailMessageDto message) {
        emailMessageService.addMessage(message);
    }

    @PostMapping("sms")
    public void addSmsMessages(@RequestBody @Valid SmsDto message) {
        smsService.addMessage(message);
    }
}

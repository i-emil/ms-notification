package com.troojer.msnotification.client;

import ch.qos.logback.classic.Logger;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import com.troojer.msnotification.model.EmailMessageDto;
import com.troojer.msnotification.model.enm.SendingStatus;
import com.troojer.msnotification.model.exception.EmailException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MailJetEmailSenderClient {

    private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

    private final String API_KEY = "8e0b5478c3f73e4aeaaf911efefd9af0";
    private final String API_Secret = "29d3163ac9789cae33369dd73096b380";
    private final String VERSION = "v3.1";

    public Map<Long, SendingStatus> sendEmails(List<EmailMessageDto> messages) {
        Map<Long, SendingStatus> messagesStatus = new HashMap<>();
        messages.forEach(m -> {
            Pair<Long, SendingStatus> mPair = sendEmail(m);
            messagesStatus.put(mPair.getFirst(), mPair.getSecond());
        });
        logger.info("sendEmails() start: {}", messages);
        return messagesStatus;
    }

    private Pair<Long, SendingStatus> sendEmail(EmailMessageDto message) {
        MailjetClient client = new MailjetClient(API_KEY, API_Secret, new ClientOptions(VERSION));
        MailjetRequest request;
        try {
            request = getRequest(message.getEmail(), message);
        } catch (EmailException exc) {
            logger.warn("sendEmails(); exc: ", exc);
            throw new EmailException(exc.getMessage());
        }
        try {
            MailjetResponse response = client.post(request);
            logger.info("sendEmails(), response: {}", response);
            return Pair.of(message.getId(), SendingStatus.SEND);
        } catch (MailjetException | MailjetSocketTimeoutException e) {
            logger.warn("sendEmails(), exc: ", e);
        }
        return Pair.of(message.getId(), SendingStatus.PENDING);
    }

    private MailjetRequest getRequest(String email, EmailMessageDto message) {
        if (!email.matches("^(.+)@(.+)$"))
            throw new EmailException("badEmail(" + email + ")");
        return new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "no-reply@troojer.com")
                                        .put("Name", "TRooJR"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", email)
                                                .put("Name", email)))
                                .put(Emailv31.Message.SUBJECT, message.getSubject())
                                .put(Emailv31.Message.HTMLPART, message.getMessageText())));
    }
}


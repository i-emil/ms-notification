package com.troojer.msnotification.client;

import ch.qos.logback.classic.Logger;
import com.troojer.msnotification.model.SendingStatus;
import com.troojer.msnotification.model.SmsDto;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.troojer.msnotification.model.SendingStatus.*;

@Component
public class SoftlineSmsSenderClient {

    private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

    private final String username = "sftdemo";
    private final String apiKey = "oOcLW3An";
    private final String senderName = "SOFTLINE";

    private final RestTemplate restTemplate;

    public SoftlineSmsSenderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<Long, SendingStatus> sendAllSms(List<SmsDto> smsList) {
        Map<Long, SendingStatus> messagesStatus = new HashMap<>();
        smsList.forEach(s -> {
            Pair<Long, SendingStatus> mPair = sendSms(s);
            messagesStatus.put(mPair.getFirst(), mPair.getSecond());
        });
        logger.info("sendEmails() start: {}", smsList);
        return messagesStatus;
    }

    public Pair<Long, SendingStatus> sendSms(SmsDto sms) {
        String url = "http://gw.soft-line.az/sendsms?user=" + username + "&password=" + apiKey + "&gsm=" + sms.getPhoneNumber() + "&from=" + senderName + "&text=" + sms.getMessageText();
        SendingStatus sendingStatus = PENDING;
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            String response = responseEntity.getBody();
            logger.info("sendSms(); response: {}, sms: {}", response, sms);
            String responseCode = response != null ? response.substring(6, response.indexOf('&')) : "200";
            sendingStatus = switch (responseCode) {
                case "100" -> SEND;
                case "90", "25", "20", "10", "40" -> ERROR;
                default -> PENDING;
            };
        } catch (Exception e) {
            logger.warn("sendSms(), sms: {}", sms);
        }
        long id = sms.getId() == null ? -1L : sms.getId();
        return Pair.of(id, sendingStatus);
    }
}

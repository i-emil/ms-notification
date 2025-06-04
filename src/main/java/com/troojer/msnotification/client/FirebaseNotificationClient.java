package com.troojer.msnotification.client;

import com.troojer.msnotification.model.PushNotificationRequest;
import com.troojer.msnotification.model.PushNotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class FirebaseNotificationClient {

    private static final String AUTHORIZATION = "Authorization";
    private final RestTemplate restTemplate;
    @Value("${firebase-push-notification.api-url}")
    private String FIREBASE_URL;

    @Value("${firebase-push-notification.server-key}")
    private String SERVER_KEY;

    public PushNotificationResponse push(PushNotificationRequest firebasePushNotificationRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, "key=" + SERVER_KEY);

        HttpEntity<PushNotificationRequest> entity = new HttpEntity<>(firebasePushNotificationRequest, headers);

        ResponseEntity<PushNotificationResponse> response = restTemplate.postForEntity(FIREBASE_URL, entity,
                PushNotificationResponse.class);

        return response.getBody();
    }
}

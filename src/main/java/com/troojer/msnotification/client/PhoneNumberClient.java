package com.troojer.msnotification.client;

import ch.qos.logback.classic.Logger;
import com.troojer.msnotification.model.exception.ClientException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class PhoneNumberClient {

    private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());
    private final RestTemplate restTemplate;

    @Value("${client.localization.url}")
    private String url;

    public PhoneNumberClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getPhoneRegion(String number) throws ClientException {
        try {
            return restTemplate.getForEntity(url + "localization/phones/" + "region/" + number, String.class).getBody();
        } catch (RestClientResponseException exc) {
            logger.warn("getStandardFormatNumber(); warn: ", exc);
            throw new ClientException(exc.getMessage());
        } catch (Exception exc) {
            logger.warn("getStandardFormatNumber(); warn: ", exc);
            throw new ClientException();
        }
    }
}
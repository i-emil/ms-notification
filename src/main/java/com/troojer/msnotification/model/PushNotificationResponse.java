package com.troojer.msnotification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushNotificationResponse {

    private Integer success;
    private Integer failure;
    private long multicast_id;
    private Integer canonical_ids;
    private List<Map<String, String>> results;

    public boolean isSuccess() {
        return this.success == 1;
    }
}

package com.troojer.msnotification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.troojer.msnotification.constraints.DateParameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SmsDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotBlank
    private String phoneNumber;

    @JsonProperty(access = READ_ONLY)
    private String region;

    @NotNull
    private String messageText;

    private String userId;

    @DateParameters(param = "sendingDate")
    private String sendingDate;
}
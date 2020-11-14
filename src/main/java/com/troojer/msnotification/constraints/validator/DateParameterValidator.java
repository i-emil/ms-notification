package com.troojer.msnotification.constraints.validator;

import com.troojer.msnotification.constraints.DateParameters;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParameterValidator
        implements ConstraintValidator<DateParameters, String> {

    @Override
    public boolean isValid(String sendingDate, ConstraintValidatorContext context) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (sendingDate == null) return true;
        try {
            LocalDateTime.parse(sendingDate, dateTimeFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

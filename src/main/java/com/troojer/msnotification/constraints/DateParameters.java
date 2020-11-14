package com.troojer.msnotification.constraints;

import com.troojer.msnotification.constraints.validator.DateParameterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = DateParameterValidator.class)
@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Documented
public @interface DateParameters {

    String message() default
            "Date format is wrong";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String param();

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        DateParameters[] value();
    }
}
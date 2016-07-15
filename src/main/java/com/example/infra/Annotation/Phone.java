package com.example.infra.Annotation;

import com.example.domain.validation.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Created by saeki on 2016/07/15.
 */
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Phone {
//    String message() default "please input a phone number.";
    String message() default "{customValidator.phone.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean onlyNumber() default false;

}

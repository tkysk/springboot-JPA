package com.example.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by saeki on 2016/07/15.
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private boolean onlyNumber;

    @Override
    public void initialize(Phone phone) {
        onlyNumber = phone.onlyNumber();
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext cxt) {
        if (input == null)
            return false;

        if (onlyNumber)
            return input.matches("[0-9]*");
        else
            return input.matches("[0-9()-]*");
    }
}

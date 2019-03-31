package com.ichop.linkaccount.validators;

import com.ichop.linkaccount.services.KeyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidKeyValidator implements ConstraintValidator<ValidKey, String> {

    private final KeyServices keyServices;

    @Autowired
    public ValidKeyValidator(KeyServices keyServices) {
        this.keyServices = keyServices;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.keyServices.isKeyValid(value);
    }
}

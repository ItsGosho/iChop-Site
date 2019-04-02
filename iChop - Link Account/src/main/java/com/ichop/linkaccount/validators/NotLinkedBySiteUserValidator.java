package com.ichop.linkaccount.validators;

import com.ichop.linkaccount.services.KeyServices;
import com.ichop.linkaccount.services.PlayerLinkServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class NotLinkedBySiteUserValidator implements ConstraintValidator<NotLinkedBySiteUser, String> {

    private final KeyServices keyServices;
    private final PlayerLinkServices playerLinkServices;

    @Autowired
    public NotLinkedBySiteUserValidator(KeyServices keyServices, PlayerLinkServices playerLinkServices) {
        this.keyServices = keyServices;
        this.playerLinkServices = playerLinkServices;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !this.playerLinkServices.isAccountLinkedBySiteUserUsername(value);
    }
}

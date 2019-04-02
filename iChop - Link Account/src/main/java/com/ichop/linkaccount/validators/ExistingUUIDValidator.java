package com.ichop.linkaccount.validators;

import com.ichop.linkaccount.services.PlayerLinkServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ExistingUUIDValidator implements ConstraintValidator<ExistingUUID, String> {

    private final PlayerLinkServices playerLinkServices;

    @Autowired
    public ExistingUUIDValidator(PlayerLinkServices playerLinkServices) {
        this.playerLinkServices = playerLinkServices;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.playerLinkServices.isAccountLinkedByUUID(value);
    }
}

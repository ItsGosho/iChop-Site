package com.ichop.linkaccount.validators;

import com.ichop.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.linkaccount.services.KeyServices;
import com.ichop.linkaccount.services.PlayerLinkServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class NotLinkedByUUIDKeyValidator implements ConstraintValidator<NotLinkedByUUIDKey, String> {


    private final KeyServices keyServices;
    private final PlayerLinkServices playerLinkServices;

    @Autowired
    public NotLinkedByUUIDKeyValidator(KeyServices keyServices, PlayerLinkServices playerLinkServices) {
        this.keyServices = keyServices;
        this.playerLinkServices = playerLinkServices;
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        KeyServiceModel keyServiceModel = this.keyServices.getByKey(value);
        return !this.playerLinkServices.isAccountLinkedByUUID( keyServiceModel.getPlayerUUID());
    }
}

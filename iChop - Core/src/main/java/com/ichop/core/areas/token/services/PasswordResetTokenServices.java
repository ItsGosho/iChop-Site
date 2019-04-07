package com.ichop.core.areas.token.services;

import com.ichop.core.areas.token.domain.models.binding.PasswordResetTokenCreateBindingModel;
import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface PasswordResetTokenServices {

    boolean isValid(String token);

    boolean isTokenDateExpired(PasswordResetTokenServiceModel passwordResetToken);

    PasswordResetTokenServiceModel create(PasswordResetTokenCreateBindingModel bindingModel);

    void deleteOldestByUser(UserServiceModel user);

    PasswordResetTokenServiceModel findByToken(String token);
}

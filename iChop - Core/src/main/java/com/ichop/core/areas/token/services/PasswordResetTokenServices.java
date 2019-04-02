package com.ichop.core.areas.token.services;

import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface PasswordResetTokenServices {

    boolean isValid(String token);

    PasswordResetTokenServiceModel create(UserServiceModel user);

    void deleteOldestByUser(UserServiceModel user);

    PasswordResetTokenServiceModel findByToken(String token);
}

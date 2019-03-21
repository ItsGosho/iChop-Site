package com.ichop.core.service.token;

import com.ichop.core.domain.models.service.token.PasswordResetTokenServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;

public interface PasswordResetTokenServices {

    boolean isTokenValid(String token);

    PasswordResetTokenServiceModel createToken(UserServiceModel user);

    void deleteOldestTokenByUser(UserServiceModel user);

    PasswordResetTokenServiceModel findTokenByToken(String token);
}

package com.ichop.core.domain.models.service.token;

import com.ichop.core.domain.models.service.BaseServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PasswordResetTokenServiceModel extends BaseServiceModel {

    private String token;
    private UserServiceModel user;
    private LocalDateTime expiryDate;
    private Integer TOKEN_EXPIRATION_IN_SECONDS;
}

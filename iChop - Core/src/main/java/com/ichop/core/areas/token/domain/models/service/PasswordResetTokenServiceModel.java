package com.ichop.core.areas.token.domain.models.service;

import com.ichop.core.base.BaseServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
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

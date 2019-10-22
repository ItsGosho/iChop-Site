package com.ichop.core.areas.token.domain.models.binding;

import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetTokenCreateBindingModel {


    private UserServiceModel user;
}

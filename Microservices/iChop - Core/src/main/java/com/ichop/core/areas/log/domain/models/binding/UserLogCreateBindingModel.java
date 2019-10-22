package com.ichop.core.areas.log.domain.models.binding;

import com.ichop.core.areas.log.domain.entities.UserLogType;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogCreateBindingModel {

    private String message;
    private UserServiceModel user;
    private UserLogType logType;

}

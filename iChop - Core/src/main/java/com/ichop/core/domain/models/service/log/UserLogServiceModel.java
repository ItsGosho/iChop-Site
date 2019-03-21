package com.ichop.core.domain.models.service.log;

import com.ichop.core.domain.entities.log.UserLogType;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogServiceModel extends LogServiceModel {

    private UserServiceModel user;
    private UserLogType logType;

}

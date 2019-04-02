package com.ichop.core.areas.log.services;

import com.ichop.core.areas.log.domain.entities.UserLogType;
import com.ichop.core.areas.log.domain.models.service.UserLogServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserLogServices {

    UserLogServiceModel create(String message, UserServiceModel user, UserLogType roleChange);


    List<UserLogServiceModel> findAllByUserAndLogType(UserServiceModel user, UserLogType logType);
}

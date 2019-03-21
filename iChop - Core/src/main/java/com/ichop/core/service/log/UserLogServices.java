package com.ichop.core.service.log;

import com.ichop.core.domain.entities.log.UserLogType;
import com.ichop.core.domain.models.service.log.UserLogServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;

import java.util.List;

public interface UserLogServices {

    UserLogServiceModel createUserLog(String message, UserServiceModel user, UserLogType roleChange);


    List<UserLogServiceModel> findAllByUserAndLogType(UserServiceModel user, UserLogType logType);
}

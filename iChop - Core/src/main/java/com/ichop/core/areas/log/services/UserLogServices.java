package com.ichop.core.areas.log.services;

import com.ichop.core.areas.log.domain.entities.UserLogType;
import com.ichop.core.areas.log.domain.models.binding.UserLogCreateBindingModel;
import com.ichop.core.areas.log.domain.models.service.UserLogServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserLogServices {

    UserLogServiceModel create(UserLogCreateBindingModel userLogCreateBindingModel);

    List<UserLogServiceModel> findAllByUserAndLogType(UserServiceModel user, UserLogType userLogType);
}

package com.ichop.core.areas.log.services;

import com.ichop.core.areas.log.domain.entities.UserLogType;
import com.ichop.core.areas.log.domain.models.binding.UserLogCreateBindingModel;
import com.ichop.core.areas.log.domain.models.service.UserLogServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserLogServices {

    /*
    *
    * Creates Log from user log binding model
    * @throws UserNotFoundException if the user is null
    * @returns UserLogServiceModel which is always valid
    *
    * */
    UserLogServiceModel create(UserLogCreateBindingModel bindingModel);


    /*
    *
    * Finds all of the user logs by user and logType
    * @throws UserNotFoundException if the user is null
    *
    * */
    List<UserLogServiceModel> findAllByUserAndLogType(UserServiceModel user, UserLogType userLogType);
}

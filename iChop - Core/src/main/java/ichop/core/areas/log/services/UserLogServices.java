package ichop.core.areas.log.services;

import ichop.core.areas.log.domain.entities.UserLogType;
import ichop.core.areas.log.domain.models.binding.UserLogCreateBindingModel;
import ichop.core.areas.log.domain.models.service.UserLogServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;

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

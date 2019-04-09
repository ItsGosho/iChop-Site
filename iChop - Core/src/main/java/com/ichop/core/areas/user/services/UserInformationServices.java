package com.ichop.core.areas.user.services;

import com.ichop.core.areas.user.domain.models.binding.UserUpdateProfileInformationBindingModel;
import com.ichop.core.areas.user.domain.models.service.UserInformationServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface UserInformationServices {

    /*
    *
    * Updates the current user information.In case of not existing
    * birthdate ,a exception will be catched.
    * @throws UserNotFoundException if the user is null
    *
    * */
    UserInformationServiceModel update(UserUpdateProfileInformationBindingModel userUpdateProfileInformationBindingModel);

    /*
    *
    * Get the user information by @param user.
    * In case of not existing information a null
    * will be returned.
    *
    * */
    UserInformationServiceModel getByUser(UserServiceModel user);

    /*
    *
    * Creates user information with empty and null
    * fields.
    *
    * */
    void createFirstTime(UserServiceModel user);

    /*
    *
    * Checks if the user information exists
    * by the provided user.
    *
    * */
    boolean isUserInformationExistsByUser(UserServiceModel user);

}

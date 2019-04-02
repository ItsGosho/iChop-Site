package com.ichop.core.areas.user.services;

import com.ichop.core.areas.user.domain.models.binding.UserUpdateProfileInformationBindingModel;
import com.ichop.core.areas.user.domain.models.service.UserInformationServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface UserInformationServices {

    UserInformationServiceModel update(UserUpdateProfileInformationBindingModel userUpdateProfileInformationBindingModel, UserServiceModel user);

    boolean isUserInformationExistsByUser(UserServiceModel user);

}

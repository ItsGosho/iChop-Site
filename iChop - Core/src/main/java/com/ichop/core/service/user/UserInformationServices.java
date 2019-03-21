package com.ichop.core.service.user;

import com.ichop.core.domain.models.binding.user.UserUpdateProfileInformationBindingModel;
import com.ichop.core.domain.models.service.user.UserInformationServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;

public interface UserInformationServices {

    UserInformationServiceModel update(UserUpdateProfileInformationBindingModel userUpdateProfileInformationBindingModel, UserServiceModel user);

    boolean isUserInformationExistsByUser(UserServiceModel user);

}

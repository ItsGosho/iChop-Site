package com.ichop.core.areas.user.helpers.view.user_profile;

import com.ichop.core.areas.user.domain.models.service.UserInformationServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_profile.UserInformationProfileViewModel;
import com.ichop.core.areas.user.services.UserInformationServices;
import com.ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserInformationProfileViewHelper extends BaseViewCreator {

    private final UserInformationServices userInformationServices;

    public UserInformationProfileViewHelper(ModelMapper modelMapper, UserInformationServices userInformationServices) {
        super(modelMapper);
        this.userInformationServices = userInformationServices;
    }


    public UserInformationProfileViewModel create(UserServiceModel user) {
        UserInformationServiceModel userInformationServiceModel = this.userInformationServices.getByUser(user);

        UserInformationProfileViewModel result = userInformationServiceModel != null
                ? this.modelMapper.map(userInformationServiceModel, UserInformationProfileViewModel.class)
                : null;

        return result;
    }

}

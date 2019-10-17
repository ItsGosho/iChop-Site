package com.ichop.core.areas.user.helpers.view.user_options;

import com.ichop.core.areas.user.domain.models.service.UserInformationServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_options.UserProfileOptionsInformationViewModel;
import com.ichop.core.areas.user.services.UserInformationServices;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileOptionsInformationViewHelper extends BaseViewCreator {

    private final UserServices userServices;
    private final UserInformationServices userInformationServices;

    @Autowired
    public UserProfileOptionsInformationViewHelper(ModelMapper modelMapper, UserServices userServices, UserInformationServices userInformationServices) {
        super(modelMapper);
        this.userServices = userServices;
        this.userInformationServices = userInformationServices;
    }

    public UserProfileOptionsInformationViewModel create(String username){
        UserServiceModel user = this.userServices.findUserByUsername(username);
        UserInformationServiceModel userInformation = this.userInformationServices.getByUser(user);
        UserProfileOptionsInformationViewModel userProfileOptionsInformation = this.modelMapper.map(userInformation,UserProfileOptionsInformationViewModel.class);
        userProfileOptionsInformation.setUsername(user.getUsername());

        return userProfileOptionsInformation;
    }

}

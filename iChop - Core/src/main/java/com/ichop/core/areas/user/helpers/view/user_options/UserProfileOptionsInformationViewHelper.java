package com.ichop.core.areas.user.helpers.view.user_options;

import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_options.UserProfileOptionsInformationViewModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProfileOptionsInformationViewHelper extends BaseViewCreator {

    private final UserServices userServices;

    @Autowired
    public UserProfileOptionsInformationViewHelper(ModelMapper modelMapper, UserServices userServices) {
        super(modelMapper);
        this.userServices = userServices;
    }

    public UserProfileOptionsInformationViewModel create(String username){
        UserServiceModel user = this.userServices.findUserByUsername(username);

        return this.modelMapper.map(user.getUserInformation(),UserProfileOptionsInformationViewModel.class);
    }

}

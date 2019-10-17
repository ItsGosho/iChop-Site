package com.ichop.core.areas.user.helpers.view.user_control;

import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_control.UserControlHomeViewModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseViewCreator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserControlHomeViewHelper extends BaseViewCreator {

    private final UserServices userServices;
    private final UserRoleServices userRoleServices;

    @Autowired
    protected UserControlHomeViewHelper(ModelMapper modelMapper, UserServices userServices, UserRoleServices userRoleServices) {
        super(modelMapper);
        this.userServices = userServices;
        this.userRoleServices = userRoleServices;
    }


    public UserControlHomeViewModel create(String username) {
        UserServiceModel user = this.userServices.findUserByUsername(username);

        if(user == null){
            throw new UserNotFoundException();
        }

        String role = this.userRoleServices.findHighestOfUser(user).getAuthority();

        UserControlHomeViewModel result = this.modelMapper.map(user, UserControlHomeViewModel.class);
        result.setRole(role);

        return result;
    }

}

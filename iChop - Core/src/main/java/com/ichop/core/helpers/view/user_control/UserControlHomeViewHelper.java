package com.ichop.core.helpers.view.user_control;

import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.domain.models.view.user_control.UserControlHomeViewModel;
import com.ichop.core.helpers.view.BaseViewCreator;
import com.ichop.core.service.role.UserRoleServices;
import com.ichop.core.service.user.UserServices;
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
        String role = this.userRoleServices.findHighestRoleOfUser(user).getAuthority();

        UserControlHomeViewModel result = this.modelMapper.map(user, UserControlHomeViewModel.class);
        result.setRole(role);

        return result;
    }

}

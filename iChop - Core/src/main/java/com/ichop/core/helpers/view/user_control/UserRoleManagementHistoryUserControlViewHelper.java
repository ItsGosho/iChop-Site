package com.ichop.core.helpers.view.user_control;

import com.ichop.core.domain.entities.log.UserLogType;
import com.ichop.core.domain.models.service.log.UserLogServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.domain.models.view.user_control.UserRoleManagementHistoryUserControlViewModel;
import com.ichop.core.helpers.view.BaseViewCreator;
import com.ichop.core.service.log.UserLogServices;
import com.ichop.core.service.user.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRoleManagementHistoryUserControlViewHelper extends BaseViewCreator {

    private final UserServices userServices;
    private final UserLogServices userLogServices;

    @Autowired
    protected UserRoleManagementHistoryUserControlViewHelper(ModelMapper modelMapper, UserServices userServices, UserLogServices userLogServices) {
        super(modelMapper);
        this.userServices = userServices;
        this.userLogServices = userLogServices;
    }


    public List<UserRoleManagementHistoryUserControlViewModel> create(String username) {
        UserServiceModel user = this.userServices.findUserByUsername(username);

        List<UserLogServiceModel> roleChangeLogs = this.userLogServices.findAllByUserAndLogType(user, UserLogType.ROLE_CHANGE);

        List<UserRoleManagementHistoryUserControlViewModel> result =
                roleChangeLogs
                        .stream()
                        .map(x -> this.modelMapper.map(x, UserRoleManagementHistoryUserControlViewModel.class))
                        .collect(Collectors.toList());

        return result;
    }

}

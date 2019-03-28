package com.ichop.core.helpers.view.user_control;

import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.domain.models.view.user_control.UserRoleManagementHistoryUserControlViewModel;
import com.ichop.core.domain.models.view.user_control.UserRoleManagementUserControlViewModel;
import com.ichop.core.helpers.view.BaseViewCreator;
import com.ichop.core.service.role.UserRoleServices;
import com.ichop.core.service.user.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRoleManagementUserControlViewHelper extends BaseViewCreator {

    private final UserServices userServices;
    private final UserRoleServices userRoleServices;
    private final UserRoleManagementHistoryUserControlViewHelper userRoleManagementHistoryUserControlViewHelper;

    @Autowired
    protected UserRoleManagementUserControlViewHelper(ModelMapper modelMapper, UserServices userServices, UserRoleServices userRoleServices, UserRoleManagementHistoryUserControlViewHelper userRoleManagementHistoryUserControlViewHelper) {
        super(modelMapper);
        this.userServices = userServices;
        this.userRoleServices = userRoleServices;
        this.userRoleManagementHistoryUserControlViewHelper = userRoleManagementHistoryUserControlViewHelper;
    }


    public UserRoleManagementUserControlViewModel create(String username) {
        UserServiceModel user = this.userServices.findUserByUsername(username);

        String currentRole = this.userRoleServices.findHighestRoleOfUser(user) != null ? this.userRoleServices.findHighestRoleOfUser(user).getAuthority() : null;
        String nextRole = this.userRoleServices.getUserNextRole(this.userRoleServices.findHighestRoleOfUser(user)) != null ? this.userRoleServices.getUserNextRole(this.userRoleServices.findHighestRoleOfUser(user)).getAuthority() : null;
        String previousRole = this.userRoleServices.getUserPreviousRole(this.userRoleServices.findHighestRoleOfUser(user)) != null ? this.userRoleServices.getUserPreviousRole(this.userRoleServices.findHighestRoleOfUser(user)).getAuthority() : null;
        List<UserRoleManagementHistoryUserControlViewModel> roleHistory = this.userRoleManagementHistoryUserControlViewHelper.create(username);

        UserRoleManagementUserControlViewModel result = this.modelMapper.map(user, UserRoleManagementUserControlViewModel.class);
        result.setRole(currentRole);
        result.setPreviousRole(previousRole);
        result.setNextRole(nextRole);
        result.setRoleChangeHistory(roleHistory);

        return result;
    }

}

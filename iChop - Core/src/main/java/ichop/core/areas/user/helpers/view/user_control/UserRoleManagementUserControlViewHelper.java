package ichop.core.areas.user.helpers.view.user_control;

import ichop.core.areas.role.services.UserRoleServices;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.domain.models.view.user_control.UserRoleManagementHistoryUserControlViewModel;
import ichop.core.areas.user.domain.models.view.user_control.UserRoleManagementUserControlViewModel;
import ichop.core.areas.user.exceptions.UserNotFoundException;
import ichop.core.areas.user.services.UserServices;
import ichop.core.base.BaseViewCreator;
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

        if(user == null){
            throw new UserNotFoundException();
        }

        String currentRole = this.userRoleServices.findHighestOfUser(user) != null ? this.userRoleServices.findHighestOfUser(user).getAuthority() : null;
        String nextRole = this.userRoleServices.getUserNextRole(this.userRoleServices.findHighestOfUser(user)) != null ? this.userRoleServices.getUserNextRole(this.userRoleServices.findHighestOfUser(user)).getAuthority() : null;
        String previousRole = this.userRoleServices.getUserPreviousRole(this.userRoleServices.findHighestOfUser(user)) != null ? this.userRoleServices.getUserPreviousRole(this.userRoleServices.findHighestOfUser(user)).getAuthority() : null;
        List<UserRoleManagementHistoryUserControlViewModel> roleHistory = this.userRoleManagementHistoryUserControlViewHelper.create(username);

        UserRoleManagementUserControlViewModel result = this.modelMapper.map(user, UserRoleManagementUserControlViewModel.class);
        result.setRole(currentRole);
        result.setPreviousRole(previousRole);
        result.setNextRole(nextRole);
        result.setRoleChangeHistory(roleHistory);

        return result;
    }

}

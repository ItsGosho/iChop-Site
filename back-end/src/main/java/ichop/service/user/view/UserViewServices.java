package ichop.service.user.view;

import ichop.domain.models.view.user_control.UserControlHomeViewModel;
import ichop.domain.models.view.user_control.UserControlRoleManagementViewModel;
import ichop.domain.models.view.user_profile.UserProfileViewModel;
import ichop.domain.models.view.user_all.UsersAllViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserViewServices {

    UserProfileViewModel getByUsername(String username);

    Page<UsersAllViewModel> listAllByPage(Pageable pageable);

    Page<UsersAllViewModel> findUsersByUsernameContains(String containingWord,Pageable pageable);

    Page<UsersAllViewModel> findUsersByRole(String role, Pageable pageable);

    UserControlHomeViewModel getUserControlHomeViewModel(String userUsername);

    UserControlRoleManagementViewModel getUserControlRoleManagementViewModel(String userUsername);

}

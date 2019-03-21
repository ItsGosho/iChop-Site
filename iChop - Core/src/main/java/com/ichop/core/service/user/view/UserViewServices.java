package com.ichop.core.service.user.view;

import com.ichop.core.domain.models.view.user_all.UsersAllViewModel;
import com.ichop.core.domain.models.view.user_control.UserControlHomeViewModel;
import com.ichop.core.domain.models.view.user_control.UserControlRoleManagementViewModel;
import com.ichop.core.domain.models.view.user_options.UserProfileOptionsInformationViewModel;
import com.ichop.core.domain.models.view.user_profile.UserProfileViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserViewServices {

    UserProfileViewModel getUserProfileViewModel(String username);

    UserProfileOptionsInformationViewModel getUserProfileOptionsInformationViewModel(String username);

    Page<UsersAllViewModel> listAllByPage(Pageable pageable);

    Page<UsersAllViewModel> findUsersByUsernameContains(String containingWord,Pageable pageable);

    Page<UsersAllViewModel> findUsersByRole(String role, Pageable pageable);

    UserControlHomeViewModel getUserControlHomeViewModel(String userUsername);

    UserControlRoleManagementViewModel getUserControlRoleManagementViewModel(String userUsername);

}

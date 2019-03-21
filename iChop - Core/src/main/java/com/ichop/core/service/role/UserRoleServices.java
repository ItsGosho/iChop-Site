package com.ichop.core.service.role;

import com.ichop.core.domain.entities.users.UserRoles;
import com.ichop.core.domain.models.service.role.UserRoleServiceModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;

public interface UserRoleServices {

    UserRoleServiceModel createRole(UserRoles userRoles);

    UserRoleServiceModel findHighestRoleOfUser(UserServiceModel user);

    UserRoleServiceModel getUserNextRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel getUserPreviousRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel findUserRoleByAuthority(String authority);
}

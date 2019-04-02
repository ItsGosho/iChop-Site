package com.ichop.core.areas.role.services;

import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface UserRoleServices {

    UserRoleServiceModel create(UserRoles userRoles);

    UserRoleServiceModel findHighestOfUser(UserServiceModel user);

    UserRoleServiceModel getUserNextRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel getUserPreviousRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel findByAuthority(String authority);
}

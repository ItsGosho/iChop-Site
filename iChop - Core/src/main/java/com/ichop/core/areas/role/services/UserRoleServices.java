package com.ichop.core.areas.role.services;

import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

public interface UserRoleServices {

    /*
    *
    * Creates user role which must be included in @param UserRoles,
    * in case of existing role ,her instance will be returned and no
    * new will be created.
    *
    * */
    UserRoleServiceModel create(UserRoles userRoles);

    /*
    *
    * Finds the highest role of user ,by sorting the
    * user roles by UserRoles enumeration ,in their
    * corresponding ascending order.
    *
    * */
    UserRoleServiceModel findHighestOfUser(UserServiceModel user);

    /*
    *
    * Gets the next user next role,by the next to his
    * in the UserRoles enumeration.
    *
    * */
    UserRoleServiceModel getUserNextRole(UserRoleServiceModel userRoleServiceModel);

    /*
     *
     * Gets the next user previous role,by the previous to his
     * in the UserRoles enumeration.
     *
     * */
    UserRoleServiceModel getUserPreviousRole(UserRoleServiceModel userRoleServiceModel);

    /*
    *
    * Finds role a role by authority,in case of not found role,
    * a null will be returned.
    *
    * */
    UserRoleServiceModel findByAuthority(String authority);
}

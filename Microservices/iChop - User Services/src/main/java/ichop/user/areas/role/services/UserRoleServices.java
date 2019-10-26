package ichop.user.areas.role.services;

import ichop.user.areas.role.domain.enums.UserRoles;
import ichop.user.areas.role.domain.models.service.UserRoleServiceModel;
import ichop.user.areas.user.domain.models.service.UserServiceModel;
import ichop.user.common.service.BaseService;

public interface UserRoleServices extends BaseService<UserRoleServiceModel> {


    UserRoleServiceModel create(UserRoles userRoles);

    UserRoleServiceModel findHighestOfUser(UserServiceModel user);

    UserRoleServiceModel getUserNextRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel getUserPreviousRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel findByAuthority(String authority);
}

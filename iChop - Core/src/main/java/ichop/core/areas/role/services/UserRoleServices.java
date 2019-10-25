package ichop.core.areas.role.services;

import ichop.core.areas.role.domain.enums.UserRoles;
import ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.common.service.BaseService;

public interface UserRoleServices extends BaseService<UserRoleServiceModel> {


    UserRoleServiceModel create(UserRoles userRoles);

    UserRoleServiceModel findHighestOfUser(UserServiceModel user);

    UserRoleServiceModel getUserNextRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel getUserPreviousRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel findByAuthority(String authority);
}

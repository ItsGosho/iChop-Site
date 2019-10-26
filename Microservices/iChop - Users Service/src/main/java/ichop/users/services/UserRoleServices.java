package ichop.users.services;

import ichop.users.domain.enums.UserRoles;
import ichop.users.domain.models.service.UserRoleServiceModel;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.common.service.BaseService;

public interface UserRoleServices extends BaseService<UserRoleServiceModel> {


    UserRoleServiceModel create(UserRoles userRoles);

    UserRoleServiceModel findHighestOfUser(UserServiceModel user);

    UserRoleServiceModel getUserNextRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel getUserPreviousRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel findByAuthority(String authority);
}

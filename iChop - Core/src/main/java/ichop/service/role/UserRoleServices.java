package ichop.service.role;

import ichop.domain.entities.users.UserRoles;
import ichop.domain.models.service.role.UserRoleServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface UserRoleServices {

    UserRoleServiceModel createRole(UserRoles userRoles);

    UserRoleServiceModel findHighestRoleOfUser(UserServiceModel user);

    UserRoleServiceModel getUserNextRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel getUserPreviousRole(UserRoleServiceModel userRoleServiceModel);

    UserRoleServiceModel findUserRoleByAuthority(String authority);
}

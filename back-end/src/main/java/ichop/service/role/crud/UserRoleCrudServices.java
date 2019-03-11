package ichop.service.role.crud;

import ichop.domain.models.service.role.UserRoleServiceModel;

public interface UserRoleCrudServices {


    UserRoleServiceModel save(UserRoleServiceModel userRole);
    UserRoleServiceModel findUserRoleByAuthority(String authority);

}

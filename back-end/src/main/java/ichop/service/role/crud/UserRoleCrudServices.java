package ichop.service.role.crud;

import ichop.domain.entities.users.UserRole;
import ichop.domain.models.service.user.UserRoleServiceModel;

public interface UserRoleCrudServices {


    UserRoleServiceModel save(UserRoleServiceModel userRole);
    UserRoleServiceModel findUserRoleByAuthority(String authority);

}

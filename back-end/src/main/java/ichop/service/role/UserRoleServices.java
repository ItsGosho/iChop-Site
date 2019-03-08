package ichop.service.role;

import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRole;
import ichop.domain.entities.users.UserRoles;
import ichop.domain.models.service.user.UserRoleServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface UserRoleServices {

    UserRoleServiceModel create(UserRoles userRoles);

    UserRoleServiceModel getRole(UserServiceModel userServiceModel);

}

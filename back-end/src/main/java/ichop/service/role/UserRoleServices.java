package ichop.service.role;

import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRole;

public interface UserRoleServices {

    UserRole create(UserRoles userRoles);

    UserRole getRole(User user);

}

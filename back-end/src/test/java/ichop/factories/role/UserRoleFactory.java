package ichop.factories.role;

import ichop.domain.entities.users.UserRole;
import ichop.service.role.UserRoles;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public interface UserRoleFactory {

    Set<UserRole> bakeRoles(UserRoles... userRoles);
}

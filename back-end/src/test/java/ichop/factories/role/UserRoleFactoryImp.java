package ichop.factories.role;

import ichop.domain.entities.users.UserRole;
import ichop.service.role.UserRoles;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class UserRoleFactoryImp implements UserRoleFactory {

    @Override
    public Set<UserRole> bakeRoles(UserRoles... userRoles) {

        Set<UserRole> userRolesSet = new LinkedHashSet<>();

        for (UserRoles userRoleEnum : userRoles) {

            UserRole userRole = new UserRole();
            userRole.setId(UUID.randomUUID().toString());
            userRole.setAuthority(userRoleEnum.name());

            userRolesSet.add(userRole);
        }

        return userRolesSet;
    }
}

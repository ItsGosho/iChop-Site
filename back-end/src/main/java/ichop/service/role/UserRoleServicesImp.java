package ichop.service.role;

import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRole;
import ichop.exceptions.user.UserCannotBeNullException;
import ichop.repository.user.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServicesImp implements UserRoleServices {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServicesImp(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole create(UserRoles userRoles) {

        String authority = userRoles.name().toUpperCase();
        UserRole userRole = this.userRoleRepository.findUserRoleByAuthority(userRoles.name().toUpperCase());

        if(this.userRoleRepository.findUserRoleByAuthority(authority)!=null){
            return  userRole;
        }

        userRole = new UserRole();
        userRole.setAuthority(authority);
        this.userRoleRepository.save(userRole);

        return userRole;
    }

    @Override
    public UserRole getRole(User user) {

        if (user == null) {
            throw new UserCannotBeNullException();
        }

        return (UserRole) user
                .getAuthorities()
                .stream()
                .min((x1, x2) -> Integer.compare(UserRoles.valueOf(((GrantedAuthority) x2).getAuthority()).ordinal(), UserRoles.valueOf(((GrantedAuthority) x1).getAuthority()).ordinal()))
                .orElse(null);
    }
}

package ichop.service.role;

import ichop.domain.entities.users.UserRole;
import ichop.repository.user.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}

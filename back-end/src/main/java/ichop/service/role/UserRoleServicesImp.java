package ichop.service.role;

import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRole;
import ichop.domain.entities.users.UserRoles;
import ichop.domain.models.service.user.UserRoleServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.user.UserCannotBeNullException;
import ichop.repository.user.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServicesImp implements UserRoleServices {

    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServicesImp(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRoleServiceModel create(UserRoles userRoles) {

        String authority = userRoles.name().toUpperCase();
        UserRole userRole = this.userRoleRepository.findUserRoleByAuthority(userRoles.name().toUpperCase());

        if(this.userRoleRepository.findUserRoleByAuthority(authority)!=null){
            return  this.modelMapper.map(userRole,UserRoleServiceModel.class);
        }

        userRole = new UserRole();
        userRole.setAuthority(authority);
        this.userRoleRepository.save(userRole);

        return this.modelMapper.map(userRole,UserRoleServiceModel.class);
    }

    @Override
    public UserRoleServiceModel getRole(UserServiceModel userServiceModel) {

        if (userServiceModel == null) {
            throw new UserCannotBeNullException();
        }

        return userServiceModel
                .getAuthorities()
                .stream()
                .min((x1, x2) -> Integer.compare(UserRoles.valueOf(x2.getAuthority()).ordinal(), UserRoles.valueOf(x1.getAuthority()).ordinal()))
                .orElse(null);
    }
}

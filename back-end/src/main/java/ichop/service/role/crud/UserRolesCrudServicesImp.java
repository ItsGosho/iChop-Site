package ichop.service.role.crud;

import ichop.domain.entities.users.UserRole;
import ichop.domain.models.service.user.UserRoleServiceModel;
import ichop.repository.user.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRolesCrudServicesImp implements UserRoleCrudServices {

    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRolesCrudServicesImp(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserRoleServiceModel save(UserRoleServiceModel userRole) {

        UserRole entityUserRole = this.modelMapper.map(userRole, UserRole.class);
        this.userRoleRepository.save(entityUserRole);

        return this.modelMapper.map(entityUserRole, UserRoleServiceModel.class);
    }

    @Override
    public UserRoleServiceModel findUserRoleByAuthority(String authority) {

        UserRole entityUserRole = this.userRoleRepository.findUserRoleByAuthority(authority);

        if(entityUserRole != null){
            return this.modelMapper.map(entityUserRole,UserRoleServiceModel.class);
        }

        return null;
    }
}

package ichop.service.role;

import ichop.domain.entities.users.UserRoles;
import ichop.domain.models.service.role.UserRoleServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.role.UserRoleNotFoundException;
import ichop.exceptions.user.UserCannotBeNullException;
import ichop.service.role.crud.UserRoleCrudServices;
import org.apache.commons.lang3.EnumUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServicesImp implements UserRoleServices {

    private final UserRoleCrudServices userRoleCrudServices;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServicesImp(UserRoleCrudServices userRoleCrudServices, ModelMapper modelMapper) {
        this.userRoleCrudServices = userRoleCrudServices;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserRoleServiceModel create(UserRoles userRoles) {

        String authority = userRoles.name().toUpperCase();
        UserRoleServiceModel userRole = this.userRoleCrudServices.findUserRoleByAuthority(userRoles.name().toUpperCase());

        if (userRole != null) {
            return userRole;
        }

        userRole = new UserRoleServiceModel();
        userRole.setAuthority(authority);

        UserRoleServiceModel result = this.userRoleCrudServices.save(userRole);

        return result;
    }

    @Override
    public UserRoleServiceModel getRole(UserServiceModel user) {

        if (user == null) {
            throw new UserCannotBeNullException();
        }

        return user
                .getAuthorities()
                .stream()
                .min((x1, x2) -> Integer.compare(UserRoles.valueOf(x2.getAuthority()).ordinal(), UserRoles.valueOf(x1.getAuthority()).ordinal()))
                .orElse(null);
    }

    @Override
    public UserRoleServiceModel getNextRole(UserRoleServiceModel userRoleServiceModel) {

        UserRoles userRoles = UserRoles.valueOf(userRoleServiceModel.getAuthority().toUpperCase());

        if(!EnumUtils.isValidEnum(UserRoles.class,userRoleServiceModel.getAuthority().toUpperCase())){
            throw new UserRoleNotFoundException();
        }

        if (UserRoles.values().length-1 < userRoles.ordinal()+1) {
            return null;
        }

        UserRoles nextRole = UserRoles.values()[userRoles.ordinal() + 1];
        UserRoleServiceModel result = this.userRoleCrudServices.findUserRoleByAuthority(nextRole.name().toUpperCase());

        return this.modelMapper.map(result, UserRoleServiceModel.class);
    }

    @Override
    public UserRoleServiceModel getPreviousRole(UserRoleServiceModel userRoleServiceModel) {

        UserRoles userRoles = UserRoles.valueOf(userRoleServiceModel.getAuthority().toUpperCase());

        if(!EnumUtils.isValidEnum(UserRoles.class,userRoleServiceModel.getAuthority().toUpperCase())){
            throw new UserRoleNotFoundException();
        }

        if (userRoles.ordinal() -1 < 0) {
            return null;
        }

        UserRoles nextRole = UserRoles.values()[userRoles.ordinal() - 1];
        UserRoleServiceModel result = this.userRoleCrudServices.findUserRoleByAuthority(nextRole.name().toUpperCase());

        return this.modelMapper.map(result, UserRoleServiceModel.class);
    }
}

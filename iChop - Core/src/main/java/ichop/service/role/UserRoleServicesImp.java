package ichop.service.role;

import ichop.domain.entities.users.UserRole;
import ichop.domain.entities.users.UserRoles;
import ichop.domain.models.service.role.UserRoleServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.role.UserRoleNotFoundException;
import ichop.exceptions.user.UserCannotBeNullException;
import ichop.repository.role.UserRoleRepository;
import ichop.service.BaseService;
import org.apache.commons.lang3.EnumUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServicesImp extends BaseService<UserRole, UserRoleRepository> implements UserRoleServices {


    @Autowired
    public UserRoleServicesImp(ModelMapper modelMapper, UserRoleRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public UserRoleServiceModel createRole(UserRoles userRoles) {

        String authority = userRoles.name().toUpperCase();
        UserRoleServiceModel userRole = this.findUserRoleByAuthority(userRoles.name().toUpperCase());

        if (userRole != null) {
            return userRole;
        }

        userRole = new UserRoleServiceModel();
        userRole.setAuthority(authority);

        UserRoleServiceModel result = super.save(userRole, UserRoleServiceModel.class);

        return result;
    }

    @Override
    public UserRoleServiceModel findHighestRoleOfUser(UserServiceModel user) {

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
    public UserRoleServiceModel getUserNextRole(UserRoleServiceModel userRoleServiceModel) {

        UserRoles userRoles = UserRoles.valueOf(userRoleServiceModel.getAuthority().toUpperCase());

        if (!EnumUtils.isValidEnum(UserRoles.class, userRoleServiceModel.getAuthority().toUpperCase())) {
            throw new UserRoleNotFoundException();
        }

        if (UserRoles.values().length - 1 < userRoles.ordinal() + 1) {
            return null;
        }

        UserRoles nextRole = UserRoles.values()[userRoles.ordinal() + 1];
        UserRoleServiceModel result = this.findUserRoleByAuthority(nextRole.name().toUpperCase());

        return this.modelMapper.map(result, UserRoleServiceModel.class);
    }

    @Override
    public UserRoleServiceModel getUserPreviousRole(UserRoleServiceModel userRoleServiceModel) {

        UserRoles userRoles = UserRoles.valueOf(userRoleServiceModel.getAuthority().toUpperCase());

        if (!EnumUtils.isValidEnum(UserRoles.class, userRoleServiceModel.getAuthority().toUpperCase())) {
            throw new UserRoleNotFoundException();
        }

        if (userRoles.ordinal() - 1 < 0) {
            return null;
        }

        UserRoles nextRole = UserRoles.values()[userRoles.ordinal() - 1];
        UserRoleServiceModel result = this.findUserRoleByAuthority(nextRole.name().toUpperCase());

        return this.modelMapper.map(result, UserRoleServiceModel.class);
    }

    @Override
    public UserRoleServiceModel findUserRoleByAuthority(String authority) {
        UserRole entityUserRole = super.repository.findUserRoleByAuthority(authority);

        if (entityUserRole != null) {
            return this.modelMapper.map(entityUserRole, UserRoleServiceModel.class);
        }

        return null;
    }
}

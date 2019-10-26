package ichop.users.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.domain.entities.UserRole;
import ichop.users.domain.enums.UserRoles;
import ichop.users.domain.models.service.UserRoleServiceModel;
import ichop.users.repositories.UserRoleRepository;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.common.service.AbstractBaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ichop.users.constants.RoleLogConstants.ROLE_CREATED;

@Service
public class UserRoleServicesImp extends AbstractBaseService<UserRole,UserRoleServiceModel, UserRoleRepository> implements UserRoleServices {

    private static final Logger LOG = LogManager.getLogger(UserRoleServicesImp.class);

    @Autowired
    public UserRoleServicesImp(ObjectMapper objectMapper, UserRoleRepository repository) {
        super(objectMapper, repository);
    }

    @Override
    public UserRoleServiceModel create(UserRoles userRoles) {

        String authority = userRoles.name().toUpperCase();
        UserRoleServiceModel userRole = this.findByAuthority(userRoles.name().toUpperCase());

        if (userRole != null) {
            return userRole;
        }

        userRole = new UserRoleServiceModel();
        userRole.setAuthority(authority);

        UserRoleServiceModel result = super.save(userRole, UserRoleServiceModel.class);

        LOG.info(String.format(ROLE_CREATED,result.getAuthority()));
        return result;
    }

    @Override
    public UserRoleServiceModel findHighestOfUser(UserServiceModel user) {

        return user
                .getAuthorities()
                .stream()
                .min((x1, x2) -> Integer.compare(UserRoles.valueOf(x2.getAuthority()).ordinal(), UserRoles.valueOf(x1.getAuthority()).ordinal()))
                .orElse(null);
    }

    @Override
    public UserRoleServiceModel getUserNextRole(UserRoleServiceModel userRoleServiceModel) {

        UserRoles userRoles = UserRoles.valueOf(userRoleServiceModel.getAuthority().toUpperCase());

        if (UserRoles.values().length - 1 < userRoles.ordinal() + 1) {
            return null;
        }

        UserRoles nextRole = UserRoles.values()[userRoles.ordinal() + 1];
        UserRoleServiceModel result = this.findByAuthority(nextRole.name().toUpperCase());


        return super.objectMapper.convertValue(result, UserRoleServiceModel.class);
    }

    @Override
    public UserRoleServiceModel getUserPreviousRole(UserRoleServiceModel userRoleServiceModel) {

        UserRoles userRoles = UserRoles.valueOf(userRoleServiceModel.getAuthority().toUpperCase());

        if (userRoles.ordinal() - 1 < 0) {
            return null;
        }

        UserRoles nextRole = UserRoles.values()[userRoles.ordinal() - 1];
        UserRoleServiceModel result = this.findByAuthority(nextRole.name().toUpperCase());

        return super.objectMapper.convertValue(result, UserRoleServiceModel.class);
    }

    @Override
    public UserRoleServiceModel findByAuthority(String authority) {
        UserRole entityUserRole = this.repository.findUserRoleByAuthority(authority);

        if (entityUserRole != null) {
            return super.objectMapper.convertValue(entityUserRole, UserRoleServiceModel.class);
        }

        return null;
    }
}

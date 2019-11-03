package ichop.users.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.domain.entities.Role;
import ichop.users.domain.enums.Roles;
import ichop.users.domain.models.service.RoleServiceModel;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.repositories.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ichop.commons.service.AbstractMySQLBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ichop.users.constants.RoleLogConstants.ROLE_CREATED;

@Service
public class RoleServicesImp extends AbstractMySQLBaseService<Role, RoleServiceModel, RoleRepository> implements RoleServices {

    private static final Logger LOG = LogManager.getLogger(RoleServicesImp.class);

    @Autowired
    public RoleServicesImp(ObjectMapper objectMapper, RoleRepository repository) {
        super(objectMapper, repository);
    }

    @Override
    public RoleServiceModel create(Roles roles) {

        String authority = roles.name().toUpperCase();
        RoleServiceModel userRole = this.findByAuthority(roles.name().toUpperCase());

        if (userRole != null) {
            return userRole;
        }

        userRole = new RoleServiceModel();
        userRole.setAuthority(authority);

        RoleServiceModel result = super.save(userRole, RoleServiceModel.class);

        LOG.info(String.format(ROLE_CREATED,result.getAuthority()));
        return result;
    }

    @Override
    public RoleServiceModel findHighestOfUser(UserServiceModel user) {

        return user
                .getAuthorities()
                .stream()
                .min((x1, x2) -> Integer.compare(Roles.valueOf(x2.getAuthority()).ordinal(), Roles.valueOf(x1.getAuthority()).ordinal()))
                .orElse(null);
    }

    @Override
    public RoleServiceModel getUserNextRole(RoleServiceModel roleServiceModel) {

        Roles roles = Roles.valueOf(roleServiceModel.getAuthority().toUpperCase());

        if (Roles.values().length - 1 < roles.ordinal() + 1) {
            return null;
        }

        Roles nextRole = Roles.values()[roles.ordinal() + 1];
        RoleServiceModel result = this.findByAuthority(nextRole.name().toUpperCase());


        return super.objectMapper.convertValue(result, RoleServiceModel.class);
    }

    @Override
    public RoleServiceModel getUserPreviousRole(RoleServiceModel roleServiceModel) {

        Roles roles = Roles.valueOf(roleServiceModel.getAuthority().toUpperCase());

        if (roles.ordinal() - 1 < 0) {
            return null;
        }

        Roles nextRole = Roles.values()[roles.ordinal() - 1];
        RoleServiceModel result = this.findByAuthority(nextRole.name().toUpperCase());

        return super.objectMapper.convertValue(result, RoleServiceModel.class);
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        Role entityRole = this.repository.findUserRoleByAuthority(authority);

        if (entityRole != null) {
            return super.objectMapper.convertValue(entityRole, RoleServiceModel.class);
        }

        return null;
    }
}

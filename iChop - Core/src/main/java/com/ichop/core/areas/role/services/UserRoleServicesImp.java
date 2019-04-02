package com.ichop.core.areas.role.services;

import com.ichop.core.areas.role.domain.entities.UserRole;
import com.ichop.core.areas.role.domain.entities.UserRoles;
import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.role.exceptions.RoleNotFoundException;
import com.ichop.core.areas.role.repositories.UserRoleRepository;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserCannotBeNullException;
import com.ichop.core.base.BaseService;
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
    public UserRoleServiceModel create(UserRoles userRoles) {

        String authority = userRoles.name().toUpperCase();
        UserRoleServiceModel userRole = this.findByAuthority(userRoles.name().toUpperCase());

        if (userRole != null) {
            return userRole;
        }

        userRole = new UserRoleServiceModel();
        userRole.setAuthority(authority);

        UserRoleServiceModel result = super.save(userRole, UserRoleServiceModel.class);

        return result;
    }

    @Override
    public UserRoleServiceModel findHighestOfUser(UserServiceModel user) {

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
            throw new RoleNotFoundException();
        }

        if (UserRoles.values().length - 1 < userRoles.ordinal() + 1) {
            return null;
        }

        UserRoles nextRole = UserRoles.values()[userRoles.ordinal() + 1];
        UserRoleServiceModel result = this.findByAuthority(nextRole.name().toUpperCase());

        return this.modelMapper.map(result, UserRoleServiceModel.class);
    }

    @Override
    public UserRoleServiceModel getUserPreviousRole(UserRoleServiceModel userRoleServiceModel) {

        UserRoles userRoles = UserRoles.valueOf(userRoleServiceModel.getAuthority().toUpperCase());

        if (!EnumUtils.isValidEnum(UserRoles.class, userRoleServiceModel.getAuthority().toUpperCase())) {
            throw new RoleNotFoundException();
        }

        if (userRoles.ordinal() - 1 < 0) {
            return null;
        }

        UserRoles nextRole = UserRoles.values()[userRoles.ordinal() - 1];
        UserRoleServiceModel result = this.findByAuthority(nextRole.name().toUpperCase());

        return this.modelMapper.map(result, UserRoleServiceModel.class);
    }

    @Override
    public UserRoleServiceModel findByAuthority(String authority) {
        UserRole entityUserRole = super.repository.findUserRoleByAuthority(authority);

        if (entityUserRole != null) {
            return this.modelMapper.map(entityUserRole, UserRoleServiceModel.class);
        }

        return null;
    }
}

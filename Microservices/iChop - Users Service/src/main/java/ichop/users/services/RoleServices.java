package ichop.users.services;

import ichop.users.domain.enums.Roles;
import ichop.users.domain.models.service.RoleServiceModel;
import ichop.users.domain.models.service.UserServiceModel;
import org.ichop.commons.service.BaseService;

public interface RoleServices extends BaseService<RoleServiceModel> {


    RoleServiceModel create(Roles roles);

    RoleServiceModel findHighestOfUser(UserServiceModel user);

    RoleServiceModel getUserNextRole(RoleServiceModel roleServiceModel);

    RoleServiceModel getUserPreviousRole(RoleServiceModel roleServiceModel);

    RoleServiceModel findByAuthority(String authority);
}

package ichop.users.domain.models.service;

import ichop.users.common.domain.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleServiceModel extends BaseServiceModel {

    private String authority;

}

package ichop.core.areas.role.domain.models.service;

import ichop.core.common.domain.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleServiceModel extends BaseServiceModel {

    private String authority;

}
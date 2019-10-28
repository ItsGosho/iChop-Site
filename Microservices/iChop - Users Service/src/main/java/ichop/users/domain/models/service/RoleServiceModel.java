package ichop.users.domain.models.service;

import ichop.users.common.domain.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleServiceModel extends BaseServiceModel {

    private String authority;

}

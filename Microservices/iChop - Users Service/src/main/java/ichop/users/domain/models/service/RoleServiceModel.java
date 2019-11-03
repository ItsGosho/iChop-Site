package ichop.users.domain.models.service;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseServiceModel;

@Getter
@Setter
public class RoleServiceModel extends BaseServiceModel {

    private String authority;

}

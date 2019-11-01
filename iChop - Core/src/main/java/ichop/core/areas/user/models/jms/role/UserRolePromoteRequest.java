package ichop.core.areas.user.models.jms.role;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRolePromoteRequest extends BaseRequestModel {

    private String username;

}

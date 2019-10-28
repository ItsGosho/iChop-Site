package ichop.core.areas.user.models.jms.password.forgotten;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForgottenPasswordRequest extends BaseRequestModel {

    private String email;

}

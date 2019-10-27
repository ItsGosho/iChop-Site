package ichop.users.domain.models.jms.password.forgotten;

import ichop.users.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForgottenPasswordRequest extends BaseRequestModel {

    /*TODO: validations*/
    private String email;

}

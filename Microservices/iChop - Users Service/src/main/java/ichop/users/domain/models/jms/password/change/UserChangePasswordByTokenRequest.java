package ichop.users.domain.models.jms.password.change;

import ichop.users.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordByTokenRequest extends BaseRequestModel {

    /*TODO: validations*/
    private String password;
    private String confirmPassword;
    private String token;

}

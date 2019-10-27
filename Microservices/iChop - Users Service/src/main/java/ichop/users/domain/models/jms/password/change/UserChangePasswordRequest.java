package ichop.users.domain.models.jms.password.change;

import ichop.users.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordRequest extends BaseRequestModel {

    /*TODO: validations*/

    private String email;
    private String password;
    private String confirmPassword;

}

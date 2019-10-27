package ichop.core.areas.user.models.jms.password.change;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordRequest extends BaseRequestModel {

    private String email;
    private String password;
    private String confirmPassword;

}

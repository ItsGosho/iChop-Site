package ichop.core.areas.user.models.jms.password.change;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordByTokenRequest extends BaseRequestModel {

    private String password;
    private String confirmPassword;
    private String token;

}

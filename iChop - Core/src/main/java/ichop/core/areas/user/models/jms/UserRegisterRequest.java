package ichop.core.areas.user.models.jms;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest extends BaseRequestModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

}

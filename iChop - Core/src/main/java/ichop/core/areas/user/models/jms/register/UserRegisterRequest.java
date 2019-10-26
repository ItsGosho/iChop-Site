package ichop.core.areas.user.models.jms.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

}

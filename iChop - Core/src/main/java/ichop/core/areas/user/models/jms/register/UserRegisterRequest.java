package ichop.core.areas.user.models.jms.register;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class UserRegisterRequest extends RequestCandidate {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

}

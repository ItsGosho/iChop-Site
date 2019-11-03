package ichop.core.areas.user.models.jms.password.change;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class UserChangePasswordByTokenRequest extends RequestCandidate {

    private String password;
    private String confirmPassword;
    private String token;

}

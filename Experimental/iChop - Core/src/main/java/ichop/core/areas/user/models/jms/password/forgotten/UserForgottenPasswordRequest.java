package ichop.core.areas.user.models.jms.password.forgotten;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class UserForgottenPasswordRequest extends RequestCandidate {

    private String email;

}

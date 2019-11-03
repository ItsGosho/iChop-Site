package ichop.users.domain.models.jms.token.delete.password;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class PasswordTokenDeleteByTokenRequest extends RequestCandidate {

    private String token;

}

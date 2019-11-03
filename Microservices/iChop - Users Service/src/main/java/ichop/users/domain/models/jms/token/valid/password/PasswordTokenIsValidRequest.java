package ichop.users.domain.models.jms.token.valid.password;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordTokenIsValidRequest extends RequestCandidate {

    private String token;

}

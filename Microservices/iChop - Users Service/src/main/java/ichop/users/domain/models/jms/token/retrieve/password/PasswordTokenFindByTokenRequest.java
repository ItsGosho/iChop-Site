package ichop.users.domain.models.jms.token.retrieve.password;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordTokenFindByTokenRequest extends RequestCandidate {

    private String token;

}

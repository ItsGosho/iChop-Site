package ichop.users.domain.models.jms.token.create.password;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordTokenCreateRequest extends RequestCandidate {

    private String userId;

}

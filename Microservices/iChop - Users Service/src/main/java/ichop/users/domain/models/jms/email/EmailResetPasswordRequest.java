package ichop.users.domain.models.jms.email;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmailResetPasswordRequest extends RequestCandidate {

    private String to;
    private String token;
    private LocalDateTime expirationDate;

}

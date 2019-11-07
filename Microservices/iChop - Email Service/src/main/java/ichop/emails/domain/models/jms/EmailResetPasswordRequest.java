package ichop.emails.domain.models.jms;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmailResetPasswordRequest extends RequestCandidate {

    @NotNull
    private String to;

    @NotNull
    private String token;

    @NotNull
    private LocalDateTime expirationDate;

}

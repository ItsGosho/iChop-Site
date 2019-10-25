package ichop.emails.domain.models.jms;

import ichop.emails.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmailResetPasswordRequest extends BaseRequestModel {

    @NotNull
    private String to;

    @NotNull
    private String token;

    @NotNull
    private LocalDateTime expirationDate;

}

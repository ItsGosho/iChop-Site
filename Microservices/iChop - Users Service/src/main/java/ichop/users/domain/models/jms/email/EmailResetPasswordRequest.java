package ichop.users.domain.models.jms.email;

import ichop.users.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmailResetPasswordRequest extends BaseRequestModel {

    private String to;
    private String token;
    private LocalDateTime expirationDate;

}

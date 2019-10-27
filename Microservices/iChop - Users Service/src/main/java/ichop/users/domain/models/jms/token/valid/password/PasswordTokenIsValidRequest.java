package ichop.users.domain.models.jms.token.valid.password;

import ichop.users.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordTokenIsValidRequest extends BaseRequestModel {

    private String token;

}

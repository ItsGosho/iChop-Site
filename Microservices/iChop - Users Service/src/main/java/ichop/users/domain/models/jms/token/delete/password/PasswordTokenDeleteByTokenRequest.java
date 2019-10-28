package ichop.users.domain.models.jms.token.delete.password;

import ichop.users.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordTokenDeleteByTokenRequest extends BaseRequestModel {

    private String token;

}

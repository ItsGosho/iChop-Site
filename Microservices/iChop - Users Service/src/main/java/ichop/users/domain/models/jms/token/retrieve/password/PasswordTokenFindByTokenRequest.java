package ichop.users.domain.models.jms.token.retrieve.password;

import ichop.users.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordTokenFindByTokenRequest extends BaseRequestModel {

    private String token;

}

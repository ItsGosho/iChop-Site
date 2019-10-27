package ichop.users.domain.models.jms.token.create.password;

import ichop.users.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordTokenCreateRequest extends BaseRequestModel {

    private String userId;

}

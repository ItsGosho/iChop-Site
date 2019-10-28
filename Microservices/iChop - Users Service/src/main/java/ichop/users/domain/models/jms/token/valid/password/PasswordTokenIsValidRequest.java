package ichop.users.domain.models.jms.token.valid.password;

import ichop.users.common.domain.BaseRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordTokenIsValidRequest extends BaseRequestModel {

    private String token;

}

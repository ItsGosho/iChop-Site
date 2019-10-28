package ichop.users.domain.models.jms.token.retrieve.password;

import ichop.users.common.domain.BaseRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordTokenFindByTokenRequest extends BaseRequestModel {

    private String token;

}

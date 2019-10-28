package ichop.users.domain.models.jms.token.create.password;

import ichop.users.common.domain.BaseRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordTokenCreateRequest extends BaseRequestModel {

    private String userId;

}

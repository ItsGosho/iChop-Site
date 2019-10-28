package ichop.users.domain.models.jms.token.valid.password;

import ichop.users.common.domain.BaseReplyModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordTokenIsValidReply extends BaseReplyModel {

    private boolean isValid;


}

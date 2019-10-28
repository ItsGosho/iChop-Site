package ichop.users.domain.models.jms.token.valid.password;

import ichop.users.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordTokenIsValidReply extends BaseReplyModel {

    private boolean isValid;


    public PasswordTokenIsValidReply(boolean isValid) {
        this.isValid = isValid;
    }
}

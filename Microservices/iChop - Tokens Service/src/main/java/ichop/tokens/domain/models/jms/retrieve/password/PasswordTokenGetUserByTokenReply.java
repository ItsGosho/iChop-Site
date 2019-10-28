package ichop.tokens.domain.models.jms.retrieve.password;

import ichop.tokens.common.domain.BaseReplyModel;
import ichop.tokens.common.domain.BaseRequestModel;
import ichop.tokens.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordTokenGetUserByTokenReply extends BaseReplyModel {

    private String userId;

}

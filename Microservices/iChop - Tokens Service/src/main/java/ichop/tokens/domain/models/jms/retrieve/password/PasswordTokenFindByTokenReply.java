package ichop.tokens.domain.models.jms.retrieve.password;

import ichop.tokens.common.domain.BaseReplyModel;
import ichop.tokens.common.domain.BaseRequestModel;
import ichop.tokens.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PasswordTokenFindByTokenReply extends BaseReplyModel {

    private String id;
    private String userId;
    private String token;
    private LocalDateTime creationDate;

}

package ichop.tokens.domain.models.jms.password.create;

import ichop.tokens.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PasswordTokenCreateReply extends BaseReplyModel {

    private String id;
    private String userId;
    private String token;
    private LocalDateTime creationDate;

}

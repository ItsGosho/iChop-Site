package ichop.users.domain.models.jms.token.create;

import ichop.users.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class TokenCreateReply extends BaseReplyModel {

    private String id;
    private String userId;
    private String token;
    private LocalDateTime creationDate;

}

package ichop.tokens.domain.models.jms.create;

import ichop.tokens.common.domain.BaseReplyModel;
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

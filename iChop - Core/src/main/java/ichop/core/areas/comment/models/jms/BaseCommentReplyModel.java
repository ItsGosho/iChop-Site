package ichop.core.areas.comment.models.jms;

import ichop.core.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseCommentReplyModel extends BaseReplyModel {

    private String id;
    private String creatorUsername;
    private String content;
    private LocalDateTime createdOn;

}

package ichop.comments.domain.models.jms.create;

import ichop.comments.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class CommentCreateReply extends BaseReplyModel {

    private String id;
    private String content;
    private String creatorId;
    private LocalDateTime createdOn;

}

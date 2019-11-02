package ichop.core.areas.comment.models.jms.all;

import ichop.core.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadCommentsFindByThreadIdReply extends BaseReplyModel {

    private String creatorUsername;
    private String content;
    private LocalDateTime createdOn;
    private String threadId;
}

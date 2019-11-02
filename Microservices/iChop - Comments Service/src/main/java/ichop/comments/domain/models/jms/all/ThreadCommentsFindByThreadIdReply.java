package ichop.comments.domain.models.jms.all;

import ichop.comments.common.domain.BaseReplyModel;
import ichop.comments.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadCommentsFindByThreadIdReply extends BaseReplyModel {

    private String creatorUsername;
    private String content;
    private LocalDateTime createdOn;
    private String threadId;
}

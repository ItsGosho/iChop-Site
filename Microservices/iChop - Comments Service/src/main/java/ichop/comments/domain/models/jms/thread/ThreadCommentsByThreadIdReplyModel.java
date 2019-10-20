package ichop.comments.domain.models.jms.thread;

import ichop.comments.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThreadCommentsByThreadIdReplyModel extends BaseReplyModel {


    private String id;
    private String content;
    private String creatorId;
    private String threadId;
    private LocalDateTime createdOn;
}

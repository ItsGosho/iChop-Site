package ichop.comments.domain.models.jms.thread;

import ichop.comments.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ThreadCommentDeleteByIdReply extends BaseReplyModel {


    public ThreadCommentDeleteByIdReply(String message) {
        super(message);
    }
}

package ichop.core.areas.comment.models.jms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadCommentReplyModel extends BaseCommentReplyModel {

    private String threadId;

}

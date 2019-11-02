package ichop.core.areas.comment.models.jms.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadCommentCreateReply extends CommentCreateReply {

    private String threadId;

}

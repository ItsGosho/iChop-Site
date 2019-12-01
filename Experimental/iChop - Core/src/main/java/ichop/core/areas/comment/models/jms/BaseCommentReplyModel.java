package ichop.core.areas.comment.models.jms;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.ReplyCandidate;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseCommentReplyModel extends ReplyCandidate {

    private String id;
    private String creatorUsername;
    private String content;
    private LocalDateTime createdOn;

}

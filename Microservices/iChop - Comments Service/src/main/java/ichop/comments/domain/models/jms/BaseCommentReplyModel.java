package ichop.comments.domain.models.jms;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseCommentReplyModel extends RequestCandidate {

    private String id;
    private String creatorUsername;
    private String content;
    private LocalDateTime createdOn;

}

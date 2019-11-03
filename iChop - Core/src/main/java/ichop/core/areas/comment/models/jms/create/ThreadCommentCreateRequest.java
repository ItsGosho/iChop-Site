package ichop.core.areas.comment.models.jms.create;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class ThreadCommentCreateRequest extends RequestCandidate {

    private String content;
    private String creatorUsername;
    private String threadId;
}

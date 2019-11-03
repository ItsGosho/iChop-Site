package ichop.core.areas.comment.requester;

import ichop.core.areas.comment.models.jms.create.ThreadCommentCreateRequest;
import org.ichop.commons.domain.JmsReplyModel;

public interface ThreadCommentRequester {

    JmsReplyModel create(ThreadCommentCreateRequest request);
    JmsReplyModel findByThreadId(String threadId);
}

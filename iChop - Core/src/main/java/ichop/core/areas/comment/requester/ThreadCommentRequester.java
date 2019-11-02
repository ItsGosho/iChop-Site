package ichop.core.areas.comment.requester;

import ichop.core.areas.comment.models.jms.ThreadCommentReplyModel;
import ichop.core.areas.comment.models.jms.create.ThreadCommentCreateRequest;
import ichop.core.common.domain.EmptyReplyModel;

public interface ThreadCommentRequester {

    ThreadCommentReplyModel create(ThreadCommentCreateRequest request);
    EmptyReplyModel deleteById(String id);
    ThreadCommentReplyModel findByThreadId(String threadId);
}

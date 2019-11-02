package ichop.core.areas.comment.requester;

import ichop.core.areas.comment.models.jms.create.ThreadCommentCreateReply;
import ichop.core.areas.comment.models.jms.create.ThreadCommentCreateRequest;
import ichop.core.areas.thread.models.jms.retrieve.ThreadFindByIdReply;
import ichop.core.common.domain.EmptyReplyModel;

public interface ThreadCommentRequester {

    ThreadCommentCreateReply create(ThreadCommentCreateRequest request);
    EmptyReplyModel deleteById(String id);
    ThreadFindByIdReply findByThreadId(String threadId);
}

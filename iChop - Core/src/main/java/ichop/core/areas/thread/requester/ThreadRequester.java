package ichop.core.areas.thread.requester;

import ichop.core.areas.thread.models.jms.ThreadReply;
import ichop.core.areas.thread.models.jms.create.ThreadCreateRequest;
import org.ichop.commons.domain.EmptyReplyModel;

public interface ThreadRequester {

    ThreadReply create(ThreadCreateRequest request);
    ThreadReply increaseViews(String id);
    ThreadReply findById(String id);
    EmptyReplyModel deleteById(String id);

}

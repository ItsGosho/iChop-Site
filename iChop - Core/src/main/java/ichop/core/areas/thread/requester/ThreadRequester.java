package ichop.core.areas.thread.requester;

import ichop.core.areas.thread.models.jms.create.ThreadCreateReply;
import ichop.core.areas.thread.models.jms.create.ThreadCreateRequest;
import ichop.core.areas.thread.models.jms.delete.ThreadDeleteByIdReply;
import ichop.core.areas.thread.models.jms.increase.ThreadIncreaseViewsReply;
import ichop.core.areas.thread.models.jms.retrieve.ThreadFindByIdReply;

public interface ThreadRequester {

    ThreadCreateReply create(ThreadCreateRequest request);
    ThreadIncreaseViewsReply increaseViews(String id);
    ThreadFindByIdReply findById(String id);
    ThreadDeleteByIdReply deleteById(String id);

}

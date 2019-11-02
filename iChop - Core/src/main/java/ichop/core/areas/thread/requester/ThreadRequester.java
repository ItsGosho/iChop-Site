package ichop.core.areas.thread.requester;

import ichop.core.areas.thread.models.jms.create.ThreadCreateRequest;

public interface ThreadRequester {

    ThreadCreateReply create(ThreadCreateRequest request);
    ThreadIncreaseViewsReply increaseViews(String id);
    ThreadFindByIdReply findById(String id);
    ThreadDeleteByIdReply deleteById(String id);

}

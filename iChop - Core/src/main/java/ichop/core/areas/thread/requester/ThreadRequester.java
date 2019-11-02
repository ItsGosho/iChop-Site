package ichop.core.areas.thread.requester;

import ichop.core.areas.thread.models.jms.create.ThreadCreateReply;
import ichop.core.areas.thread.models.jms.create.ThreadCreateRequest;
import ichop.core.areas.thread.models.jms.increase.ThreadIncreaseViewsReply;

public interface ThreadRequester {

    ThreadCreateReply create(ThreadCreateRequest request);
    ThreadIncreaseViewsReply increaseViews(String id);
    Thread

}

package ichop.core.areas.thread.requesters;

import ichop.core.areas.thread.models.jms.create.ThreadCreateRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.data.domain.Pageable;

public interface ThreadRequester {

    JmsReplyModel create(ThreadCreateRequest request);
    JmsReplyModel increaseViews(String id);
    JmsReplyModel findById(String id);
    JmsReplyModel deleteById(String id);
    JmsReplyModel findAll(Pageable pageable);

}

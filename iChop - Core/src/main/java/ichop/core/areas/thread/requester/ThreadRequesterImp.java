package ichop.core.areas.thread.requester;

import ichop.core.areas.thread.models.jms.create.ThreadCreateReply;
import ichop.core.areas.thread.models.jms.create.ThreadCreateRequest;
import ichop.core.areas.thread.models.jms.delete.ThreadDeleteByIdReply;
import ichop.core.areas.thread.models.jms.delete.ThreadDeleteByIdRequest;
import ichop.core.areas.thread.models.jms.increase.ThreadIncreaseViewsReply;
import ichop.core.areas.thread.models.jms.increase.ThreadIncreaseViewsRequest;
import ichop.core.areas.thread.models.jms.retrieve.ThreadFindByIdReply;
import ichop.core.areas.thread.models.jms.retrieve.ThreadFindByIdRequest;
import ichop.core.common.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ThreadRequesterImp implements ThreadRequester {

    private final JmsHelper jmsHelper;

    private final String createDestination;
    private final String increaseViewsDestination;
    private final String findByIdDestination;
    private final String deleteByIdDestination;

    public ThreadRequesterImp(JmsHelper jmsHelper,
                              @Value("${artemis.queue.threads.create}") String createDestination,
                              @Value("${artemis.queue.threads.increase_views}") String increaseViewsDestination,
                              @Value("${artemis.queue.threads.find.by.id}") String findByIdDestination,
                              @Value("${artemis.queue.threads.delete.by.id}") String deleteByIdDestination) {
        this.jmsHelper = jmsHelper;
        this.createDestination = createDestination;
        this.increaseViewsDestination = increaseViewsDestination;
        this.findByIdDestination = findByIdDestination;
        this.deleteByIdDestination = deleteByIdDestination;
    }


    @Override
    public ThreadCreateReply create(ThreadCreateRequest request) {
        return this.jmsHelper.sendAndReceive(this.createDestination, request, ThreadCreateReply.class);
    }

    @Override
    public ThreadIncreaseViewsReply increaseViews(String id) {
        ThreadIncreaseViewsRequest request = new ThreadIncreaseViewsRequest(id);

        return this.jmsHelper.sendAndReceive(this.increaseViewsDestination, request, ThreadIncreaseViewsReply.class);
    }

    @Override
    public ThreadFindByIdReply findById(String id) {
        ThreadFindByIdRequest request = new ThreadFindByIdRequest(id);

        return this.jmsHelper.sendAndReceive(this.findByIdDestination, request, ThreadFindByIdReply.class);
    }

    @Override
    public ThreadDeleteByIdReply deleteById(String id) {
        ThreadDeleteByIdRequest request = new ThreadDeleteByIdRequest(id);

        return this.jmsHelper.sendAndReceive(this.deleteByIdDestination, request, ThreadDeleteByIdReply.class);
    }
}

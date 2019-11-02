package ichop.core.areas.thread.requester;

import ichop.core.areas.thread.models.jms.ThreadReply;
import ichop.core.areas.thread.models.jms.create.ThreadCreateRequest;
import ichop.core.areas.thread.models.jms.delete.ThreadDeleteByIdRequest;
import ichop.core.areas.thread.models.jms.increase.ThreadIncreaseViewsRequest;
import ichop.core.areas.thread.models.jms.retrieve.ThreadFindByIdRequest;
import org.ichop.commons.domain.EmptyReplyModel;
import org.ichop.commons.helpers.JmsHelper;
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
    public ThreadReply create(ThreadCreateRequest request) {
        return this.jmsHelper.sendAndReceive(this.createDestination, request, ThreadReply.class);
    }

    @Override
    public ThreadReply increaseViews(String id) {
        ThreadIncreaseViewsRequest request = new ThreadIncreaseViewsRequest(id);

        return this.jmsHelper.sendAndReceive(this.increaseViewsDestination, request, ThreadReply.class);
    }

    @Override
    public ThreadReply findById(String id) {
        ThreadFindByIdRequest request = new ThreadFindByIdRequest(id);

        return this.jmsHelper.sendAndReceive(this.findByIdDestination, request, ThreadReply.class);
    }

    @Override
    public EmptyReplyModel deleteById(String id) {
        ThreadDeleteByIdRequest request = new ThreadDeleteByIdRequest(id);

        return this.jmsHelper.sendAndReceive(this.deleteByIdDestination, request, EmptyReplyModel.class);
    }
}

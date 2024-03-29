package ichop.core.areas.thread.requesters;

import ichop.core.areas.thread.models.jms.create.ThreadCreateRequest;
import ichop.core.areas.thread.models.jms.delete.ThreadDeleteByIdRequest;
import ichop.core.areas.thread.models.jms.increase.ThreadIncreaseViewsRequest;
import ichop.core.areas.thread.models.jms.retrieve.ThreadFindByIdRequest;
import ichop.core.areas.thread.models.jms.retrieve.ThreadsFindAllRequest;
import org.ichop.commons.domain.EmptyRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ThreadRequesterImp implements ThreadRequester {

    private final JmsHelper jmsHelper;

    private final String createDestination;
    private final String increaseViewsDestination;
    private final String findByIdDestination;
    private final String deleteByIdDestination;
    private final String findAllDestination;
    private final String findTotalDestination;

    public ThreadRequesterImp(JmsHelper jmsHelper,
                              @Value("${artemis.queue.threads.create}") String createDestination,
                              @Value("${artemis.queue.threads.increase_views}") String increaseViewsDestination,
                              @Value("${artemis.queue.threads.find.by.id}") String findByIdDestination,
                              @Value("${artemis.queue.threads.delete.by.id}") String deleteByIdDestination,
                              @Value("${artemis.queue.threads.find.all}") String findAllDestination,
                              @Value("${artemis.queue.threads.find.total}") String findTotalDestination) {
        this.jmsHelper = jmsHelper;
        this.createDestination = createDestination;
        this.increaseViewsDestination = increaseViewsDestination;
        this.findByIdDestination = findByIdDestination;
        this.deleteByIdDestination = deleteByIdDestination;
        this.findAllDestination = findAllDestination;
        this.findTotalDestination = findTotalDestination;
    }


    @Override
    public JmsReplyModel create(ThreadCreateRequest request) {
        return this.jmsHelper.sendAndReceive(this.createDestination, request);
    }

    @Override
    public JmsReplyModel increaseViews(String id) {
        ThreadIncreaseViewsRequest request = new ThreadIncreaseViewsRequest(id);

        return this.jmsHelper.sendAndReceive(this.increaseViewsDestination, request);
    }

    @Override
    public JmsReplyModel findById(String id) {
        ThreadFindByIdRequest request = new ThreadFindByIdRequest(id);

        return this.jmsHelper.sendAndReceive(this.findByIdDestination, request);
    }

    @Override
    public JmsReplyModel deleteById(String id) {
        ThreadDeleteByIdRequest request = new ThreadDeleteByIdRequest(id);

        return this.jmsHelper.sendAndReceive(this.deleteByIdDestination, request);
    }

    @Override
    public JmsReplyModel findAll(Pageable pageable) {
        ThreadsFindAllRequest request = new ThreadsFindAllRequest(pageable);

        return this.jmsHelper.sendAndReceive(this.findAllDestination,request);
    }

    @Override
    public JmsReplyModel findTotal() {
        return this.jmsHelper.sendAndReceive(this.findTotalDestination,new EmptyRequest());
    }
}

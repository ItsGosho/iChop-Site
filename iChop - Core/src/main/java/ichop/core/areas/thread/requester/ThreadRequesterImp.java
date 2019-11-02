package ichop.core.areas.thread.requester;

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



}

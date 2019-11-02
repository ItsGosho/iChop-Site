package ichop.core.areas.comment.requester;

import ichop.core.common.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ThreadCommentRequesterImp implements ThreadCommentRequester {

    private final JmsHelper jmsHelper;

    private final String createDestination;
    private final String deleteByIdDestination;
    private final String findByThreadIdDestination;

    @Autowired
    public ThreadCommentRequesterImp(JmsHelper jmsHelper,
                                     @Value("${artemis.queue.comments.thread.create}") String createDestination,
                                     @Value("${artemis.queue.comments.thread.delete.by.id}") String deleteByIdDestination,
                                     @Value("${artemis.queue.comments.thread.find.by.threadId}") String findByThreadIdDestination) {

        this.jmsHelper = jmsHelper;

        this.createDestination = createDestination;
        this.deleteByIdDestination = deleteByIdDestination;
        this.findByThreadIdDestination = findByThreadIdDestination;
    }


}

package ichop.core.areas.comment.requester;

import ichop.core.areas.comment.models.jms.create.ThreadCommentCreateRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
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


    @Override
    public JmsReplyModel create(ThreadCommentCreateRequest request) {
        return null;
    }

    @Override
    public JmsReplyModel deleteById(String id) {
        return null;
    }

    @Override
    public JmsReplyModel findByThreadId(String threadId) {
        return null;
    }
}

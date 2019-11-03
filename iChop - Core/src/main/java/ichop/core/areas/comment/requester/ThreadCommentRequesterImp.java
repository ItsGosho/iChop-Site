package ichop.core.areas.comment.requester;

import ichop.core.areas.comment.models.jms.all.ThreadCommentsFindByThreadIdRequest;
import ichop.core.areas.comment.models.jms.create.ThreadCommentCreateRequest;
import ichop.core.areas.comment.models.jms.delete.ThreadCommentDeleteByIdRequest;
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
        return this.jmsHelper.sendAndReceive(this.createDestination, request);
    }

    @Override
    public JmsReplyModel deleteById(String id) {
        ThreadCommentDeleteByIdRequest request = new ThreadCommentDeleteByIdRequest(id);

        return this.jmsHelper.sendAndReceive(this.deleteByIdDestination, request);
    }

    @Override
    public JmsReplyModel findByThreadId(String threadId) {
        ThreadCommentsFindByThreadIdRequest request = new ThreadCommentsFindByThreadIdRequest(threadId);

        return this.jmsHelper.sendAndReceive(this.findByThreadIdDestination, request);
    }
}

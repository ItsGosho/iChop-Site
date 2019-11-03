package ichop.core.areas.comment.requester;

import ichop.core.areas.comment.models.CommentType;
import ichop.core.areas.comment.models.jms.delete.CommentDeleteByIdRequest;
import ichop.core.areas.comment.models.jms.is.CommentIsCreatorRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseCommentRequesterImp implements BaseCommentRequester {

    private final JmsHelper jmsHelper;

    private final String deleteByIdDestination;
    private final String isCreatorDestination;

    @Autowired
    public BaseCommentRequesterImp(JmsHelper jmsHelper,
                                   @Value("${artemis.queue.comments.delete.by.id}") String deleteByIdDestination,
                                   @Value("${artemis.queue.comments.is.creator}") String isCreatorDestination) {

        this.jmsHelper = jmsHelper;

        this.deleteByIdDestination = deleteByIdDestination;
        this.isCreatorDestination = isCreatorDestination;
    }


    @Override
    public JmsReplyModel deleteById(String commentId, CommentType type) {
        CommentDeleteByIdRequest request = new CommentDeleteByIdRequest(commentId, type);

        return this.jmsHelper.sendAndReceive(this.deleteByIdDestination, request);
    }

    @Override
    public JmsReplyModel isCreator(String threadId, String creatorUsername, CommentType type) {
        CommentIsCreatorRequest request = new CommentIsCreatorRequest(threadId, creatorUsername, type);

        return this.jmsHelper.sendAndReceive(this.isCreatorDestination, request);
    }
}

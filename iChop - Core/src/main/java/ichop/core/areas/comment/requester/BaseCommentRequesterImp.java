package ichop.core.areas.comment.requester;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.comment.models.CommentType;
import ichop.core.areas.comment.models.jms.delete.CommentDeleteByIdRequest;
import ichop.core.areas.comment.models.jms.is.CommentIsCreatorRequest;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseCommentRequesterImp implements BaseCommentRequester {

    private final JmsHelper jmsHelper;
    private final ObjectMapper objectMapper;

    private final String deleteByIdDestination;
    private final String isCreatorDestination;

    @Autowired
    public BaseCommentRequesterImp(JmsHelper jmsHelper,
                                   ObjectMapper objectMapper,
                                   @Value("${artemis.queue.comments.delete.by.id}") String deleteByIdDestination,
                                   @Value("${artemis.queue.comments.is.creator}") String isCreatorDestination) {

        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;

        this.deleteByIdDestination = deleteByIdDestination;
        this.isCreatorDestination = isCreatorDestination;
    }


    @Override
    public JmsReplyModel deleteById(String commentId, CommentType type) {
        CommentDeleteByIdRequest request = new CommentDeleteByIdRequest(commentId, type);

        return this.jmsHelper.sendAndReceive(this.deleteByIdDestination, request);
    }

    @Override
    public boolean isCreator(String threadId, String creatorUsername, CommentType type) {
        CommentIsCreatorRequest request = new CommentIsCreatorRequest(threadId, creatorUsername, type);

        JmsReplyModel reply = this.jmsHelper.sendAndReceive(this.isCreatorDestination, request);

        return reply.isSuccessful() ? this.objectMapper.convertValue(reply, BoolReply.class).getResult() : false;
    }
}

package ichop.core.areas.comment.requesters;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.comment.models.CommentType;
import ichop.core.areas.comment.models.jms.delete.CommentDeleteByIdRequest;
import ichop.core.areas.comment.models.jms.find.CreatorFindTotalCommentsRequest;
import ichop.core.areas.comment.models.jms.is.CommentIsCreatorRequest;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.domain.LongReply;
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
    private final String findCreatorTotalDestination;

    @Autowired
    public BaseCommentRequesterImp(JmsHelper jmsHelper,
                                   ObjectMapper objectMapper,
                                   @Value("${artemis.queue.comments.delete.by.id}") String deleteByIdDestination,
                                   @Value("${artemis.queue.comments.is.creator}") String isCreatorDestination,
                                   @Value("${artemis.queue.comments.find.creator.total}") String findCreatorTotalDestination) {

        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;

        this.deleteByIdDestination = deleteByIdDestination;
        this.isCreatorDestination = isCreatorDestination;
        this.findCreatorTotalDestination = findCreatorTotalDestination;
    }


    @Override
    public JmsReplyModel deleteById(String commentId, CommentType type) {
        CommentDeleteByIdRequest request = new CommentDeleteByIdRequest(commentId, type);

        return this.jmsHelper.sendAndReceive(this.deleteByIdDestination, request);
    }

    @Override
    public boolean isCreator(String commentId, String creatorUsername, CommentType type) {
        CommentIsCreatorRequest request = new CommentIsCreatorRequest(commentId, creatorUsername, type);

        JmsReplyModel reply = this.jmsHelper.sendAndReceive(this.isCreatorDestination, request);

        return reply.isSuccessful() ? this.objectMapper.convertValue(reply.getData(), BoolReply.class).getResult() : false;
    }

    @Override
    public JmsReplyModel totalComments(String username) {
        CreatorFindTotalCommentsRequest request = new CreatorFindTotalCommentsRequest(username);

        JmsReplyModel reply = this.jmsHelper.sendAndReceive(this.findCreatorTotalDestination, request);

        return this.jmsHelper.sendAndReceive(this.findCreatorTotalDestination, request);
    }
}

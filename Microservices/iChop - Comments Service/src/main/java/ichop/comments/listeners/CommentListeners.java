package ichop.comments.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.domain.models.jms.delete.CommentDeleteByIdRequest;
import ichop.comments.domain.models.jms.is.CommentIsCreatorRequest;
import ichop.comments.services.GenericCommentServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.domain.EmptyReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.comments.constants.CommentReplyConstants.COMMENT_DELETE_SUCCESSFUL;
import static ichop.comments.constants.CommentReplyConstants.FETCH_SUCCESSFUL;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

@Component
public class CommentListeners extends BaseListener {

    private final GenericCommentServices genericCommentServices;

    @Autowired
    protected CommentListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, GenericCommentServices genericCommentServices) {
        super(jmsHelper, objectMapper);
        this.genericCommentServices = genericCommentServices;
    }


    @JmsValidate(model = CommentDeleteByIdRequest.class)
    @JmsAfterReturn(message = COMMENT_DELETE_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.comments.delete.by.id}", containerFactory = QUEUE)
    public EmptyReply deleteById(Message message) {
        CommentDeleteByIdRequest requestModel = this.jmsHelper.toModel(message, CommentDeleteByIdRequest.class);

        this.genericCommentServices.deleteById(requestModel.getId(), requestModel.getType());

        return new EmptyReply();
    }

    @JmsValidate(model = CommentIsCreatorRequest.class)
    @JmsAfterReturn(message = FETCH_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.comments.is.creator}", containerFactory = QUEUE)
    public BoolReply isCreator(Message message) {
        CommentIsCreatorRequest requestModel = this.jmsHelper.toModel(message, CommentIsCreatorRequest.class);

        boolean isCreator = this.genericCommentServices.isCreator(requestModel.getId(), requestModel.getCreatorUsername(), requestModel.getType());

        return new BoolReply(isCreator);
    }

}

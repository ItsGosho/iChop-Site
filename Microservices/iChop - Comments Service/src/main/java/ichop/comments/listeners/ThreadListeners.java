package ichop.comments.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.common.aop.JmsAfterReturn;
import ichop.comments.common.aop.JmsValidate;
import ichop.comments.common.helpers.JmsHelper;
import ichop.comments.domain.models.jms.thread.ThreadCommentCreateReply;
import ichop.comments.domain.models.jms.thread.ThreadCommentCreateRequest;
import ichop.comments.domain.models.jms.thread.ThreadCommentDeleteByIdRequest;
import ichop.comments.domain.models.service.ThreadCommentServiceModel;
import ichop.comments.services.ThreadCommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.comments.constants.CommentReplyConstants.COMMENT_CREATED_SUCCESSFUL;

@Component
public class ThreadListeners {

    private final JmsHelper jmsHelper;
    private final ObjectMapper objectMapper;
    private final ThreadCommentServices threadCommentServices;

    @Autowired
    public ThreadListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, ThreadCommentServices threadCommentServices) {
        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;
        this.threadCommentServices = threadCommentServices;
    }


    @JmsValidate(model = ThreadCommentCreateRequest.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.comment.thread.create}", containerFactory = "queueFactory")
    public ThreadCommentCreateReply create(Message message) {
        ThreadCommentCreateRequest requestModel = this.jmsHelper.getResultModel(message, ThreadCommentCreateRequest.class);

        ThreadCommentServiceModel threadComment = this.objectMapper.convertValue(requestModel, ThreadCommentServiceModel.class);

        ThreadCommentCreateReply replyModel = this.threadCommentServices.save(threadComment, ThreadCommentCreateReply.class);
        replyModel.setMessage(COMMENT_CREATED_SUCCESSFUL);

        return replyModel;
    }

    @JmsValidate(model = ThreadCommentDeleteByIdRequest.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.comment.thread.delete_by_id}", containerFactory = "queueFactory")
    public ThreadCommentCreateReply deleteById(Message message) {
        ThreadCommentDeleteByIdRequest requestModel = this.jmsHelper.getResultModel(message, ThreadCommentDeleteByIdRequest.class);

        ThreadCommentServiceModel threadComment = this.objectMapper.convertValue(requestModel, ThreadCommentServiceModel.class);

        ThreadCommentCreateReply replyModel = this.threadCommentServices.save(threadComment, ThreadCommentCreateReply.class);
        replyModel.setMessage(COMMENT_CREATED_SUCCESSFUL);

        return replyModel;
    }

}

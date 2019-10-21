package ichop.comments.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.common.aop.JmsAfterReturn;
import ichop.comments.common.aop.JmsValidate;
import ichop.comments.common.constants.JmsFactories;
import ichop.comments.common.helpers.JmsHelper;
import ichop.comments.domain.models.jms.thread.*;
import ichop.comments.domain.models.service.CommentServiceModel;
import ichop.comments.domain.models.service.ThreadCommentServiceModel;
import ichop.comments.services.ThreadCommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import java.util.List;

import static ichop.comments.common.constants.JmsFactories.QUEUE;
import static ichop.comments.constants.CommentReplyConstants.*;

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
    @JmsListener(destination = "${artemis.queue.comment.thread.create}", containerFactory = QUEUE)
    public ThreadCommentCreateReply create(Message message) {
        ThreadCommentCreateRequest requestModel = this.jmsHelper.getResultModel(message, ThreadCommentCreateRequest.class);

        ThreadCommentServiceModel threadComment = this.objectMapper.convertValue(requestModel, ThreadCommentServiceModel.class);

        ThreadCommentCreateReply replyModel = this.threadCommentServices.save(threadComment, ThreadCommentCreateReply.class);
        replyModel.setMessage(COMMENT_CREATED_SUCCESSFUL);

        return replyModel;
    }

    @JmsValidate(model = ThreadCommentDeleteByIdRequest.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.comment.thread.delete_by_id}", containerFactory = QUEUE)
    public ThreadCommentDeleteByIdReply deleteById(Message message) {
        ThreadCommentDeleteByIdRequest requestModel = this.jmsHelper.getResultModel(message, ThreadCommentDeleteByIdRequest.class);

        this.threadCommentServices.deleteById(requestModel.getId());

        return new ThreadCommentDeleteByIdReply(COMMENT_DELETE_SUCCESSFUL);
    }

    @JmsValidate(model = ThreadCommentsByThreadIdRequest.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.comment.thread.all_by_threadId}", containerFactory = QUEUE)
    public ThreadCommentsByThreadIdReply allByThreadId(Message message) {
        ThreadCommentDeleteByIdRequest requestModel = this.jmsHelper.getResultModel(message, ThreadCommentDeleteByIdRequest.class);

        List<ThreadCommentServiceModel> comments = this.threadCommentServices.findAllByThreadId(requestModel.getId());

        return new ThreadCommentsByThreadIdReply(COMMENT_DELETE_SUCCESSFUL, comments);
    }

}

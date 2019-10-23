package ichop.comments.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.common.aop.JmsAfterReturn;
import ichop.comments.common.aop.JmsValidate;
import ichop.comments.common.helpers.BaseListener;
import ichop.comments.common.helpers.JmsHelper;
import ichop.comments.domain.models.jms.all.CommentsAllReply;
import ichop.comments.domain.models.jms.all.ThreadCommentsByThreadIdRequest;
import ichop.comments.domain.models.jms.create.ThreadCommentCreateReply;
import ichop.comments.domain.models.jms.create.ThreadCommentCreateRequest;
import ichop.comments.domain.models.jms.delete.CommentDeleteByIdReply;
import ichop.comments.domain.models.jms.delete.ThreadCommentDeleteByIdRequest;
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
public class ThreadListeners extends BaseListener {


    private final ThreadCommentServices threadCommentServices;

    @Autowired
    protected ThreadListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, ThreadCommentServices threadCommentServices) {
        super(jmsHelper, objectMapper);
        this.threadCommentServices = threadCommentServices;
    }


    @JmsValidate(model = ThreadCommentCreateRequest.class)
    @JmsAfterReturn(message = COMMENT_CREATED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.comments.thread.create}", containerFactory = QUEUE)
    public ThreadCommentCreateReply create(Message message) {
        ThreadCommentCreateRequest requestModel = this.jmsHelper.getResultModel(message, ThreadCommentCreateRequest.class);

        ThreadCommentServiceModel threadComment = this.objectMapper.convertValue(requestModel, ThreadCommentServiceModel.class);

        return this.threadCommentServices.save(threadComment, ThreadCommentCreateReply.class);
    }

    @JmsValidate(model = ThreadCommentDeleteByIdRequest.class)
    @JmsAfterReturn(message = COMMENT_DELETE_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.comments.thread.delete_by_id}", containerFactory = QUEUE)
    public CommentDeleteByIdReply deleteById(Message message) {
        ThreadCommentDeleteByIdRequest requestModel = this.jmsHelper.getResultModel(message, ThreadCommentDeleteByIdRequest.class);

        this.threadCommentServices.deleteById(requestModel.getId());

        return new CommentDeleteByIdReply();
    }

    @JmsValidate(model = ThreadCommentsByThreadIdRequest.class)
    @JmsAfterReturn(message = COMMENTS_FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.comments.thread.all_by_threadId}", containerFactory = QUEUE)
    public CommentsAllReply<ThreadCommentServiceModel> allByThreadId(Message message) {
        ThreadCommentDeleteByIdRequest requestModel = this.jmsHelper.getResultModel(message, ThreadCommentDeleteByIdRequest.class);

        List<ThreadCommentServiceModel> comments = this.threadCommentServices.findAllByThreadId(requestModel.getId());

        return new CommentsAllReply<>(comments);
    }

}

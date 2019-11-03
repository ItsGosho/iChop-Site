package ichop.comments.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.domain.models.jms.ThreadCommentReplyModel;
import ichop.comments.domain.models.jms.all.ThreadCommentsFindByThreadIdRequest;
import ichop.comments.domain.models.jms.create.ThreadCommentCreateRequest;
import ichop.comments.domain.models.jms.delete.ThreadCommentDeleteByIdRequest;
import ichop.comments.domain.models.service.ThreadCommentServiceModel;
import ichop.comments.services.ThreadCommentServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.EmptyReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import java.util.List;

import static ichop.comments.constants.CommentReplyConstants.*;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

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
    public ThreadCommentReplyModel create(Message message) {
        ThreadCommentCreateRequest requestModel = this.jmsHelper.toModel(message, ThreadCommentCreateRequest.class);

        ThreadCommentServiceModel threadComment = this.objectMapper.convertValue(requestModel, ThreadCommentServiceModel.class);

        return this.threadCommentServices.save(threadComment, ThreadCommentReplyModel.class);
    }

    @JmsValidate(model = ThreadCommentDeleteByIdRequest.class)
    @JmsAfterReturn(message = COMMENT_DELETE_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.comments.thread.delete.by.id}", containerFactory = QUEUE)
    public EmptyReply deleteById(Message message) {
        ThreadCommentDeleteByIdRequest requestModel = this.jmsHelper.toModel(message, ThreadCommentDeleteByIdRequest.class);

        this.threadCommentServices.deleteById(requestModel.getId());

        return new EmptyReply();
    }

    @JmsValidate(model = ThreadCommentsFindByThreadIdRequest.class)
    @JmsAfterReturn(message = COMMENTS_FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.comments.thread.find.by.threadId}", containerFactory = QUEUE)
    public List<ThreadCommentReplyModel> allByThreadId(Message message) {
        ThreadCommentDeleteByIdRequest requestModel = this.jmsHelper.toModel(message, ThreadCommentDeleteByIdRequest.class);

        return this.threadCommentServices.findAllByThreadId(requestModel.getId(), ThreadCommentReplyModel.class);
    }

}

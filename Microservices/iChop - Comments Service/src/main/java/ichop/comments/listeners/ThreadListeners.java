package ichop.comments.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.common.aop.JmsAfterReturn;
import ichop.comments.common.aop.JmsValidate;
import ichop.comments.common.helpers.JmsHelper;
import ichop.comments.domain.models.jms.thread.ThreadCommentCreateReplyModel;
import ichop.comments.domain.models.jms.thread.ThreadCommentCreateRequestModel;
import ichop.comments.domain.models.service.ThreadCommentServiceModel;
import ichop.comments.services.ThreadCommentServices;
import ichop.comments.services.UserProfileCommentServices;
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


    @JmsValidate(model = ThreadCommentCreateRequestModel.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.token.password.create}", containerFactory = "queueFactory")
    public ThreadCommentCreateReplyModel create(Message message) {
        ThreadCommentCreateRequestModel requestModel = this.jmsHelper.getResultModel(message, ThreadCommentCreateRequestModel.class);

        ThreadCommentServiceModel threadComment = this.objectMapper.convertValue(requestModel, ThreadCommentServiceModel.class);

        ThreadCommentCreateReplyModel replyModel = this.threadCommentServices.save(threadComment, ThreadCommentCreateReplyModel.class);
        replyModel.setMessage(COMMENT_CREATED_SUCCESSFUL);

        return replyModel;
    }

}

package ichop.comments.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.common.aop.JmsAfterReturn;
import ichop.comments.common.aop.JmsValidate;
import ichop.comments.common.domain.EmptyReplyModel;
import ichop.comments.common.helpers.BaseListener;
import ichop.comments.common.helpers.JmsHelper;
import ichop.comments.domain.models.jms.all.UserProfileCommentsByUserProfileUsernameRequest;
import ichop.comments.domain.models.jms.create.UserProfileCommentCreateRequest;
import ichop.comments.domain.models.jms.delete.UserProfileCommentDeleteByIdRequest;
import ichop.comments.domain.models.service.UserProfileCommentServiceModel;
import ichop.comments.services.UserProfileCommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import java.util.List;

import static ichop.comments.common.constants.JmsFactories.QUEUE;
import static ichop.comments.constants.CommentReplyConstants.COMMENT_CREATED_SUCCESSFUL;
import static ichop.comments.constants.CommentReplyConstants.COMMENT_DELETE_SUCCESSFUL;

@Component
public class UserProfileListeners extends BaseListener {

    private final UserProfileCommentServices userProfileCommentServices;

    @Autowired
    protected UserProfileListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, UserProfileCommentServices userProfileCommentServices) {
        super(jmsHelper, objectMapper);
        this.userProfileCommentServices = userProfileCommentServices;
    }


    @JmsValidate(model = UserProfileCommentCreateRequest.class)
    @JmsAfterReturn(message = COMMENT_CREATED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.comments.user.profile.create}", containerFactory = QUEUE)
    public UserProfileCommentCreateReply create(Message message) {
        UserProfileCommentCreateRequest requestModel = this.jmsHelper.getResultModel(message, UserProfileCommentCreateRequest.class);

        UserProfileCommentServiceModel userProfileComment = this.objectMapper.convertValue(requestModel, UserProfileCommentServiceModel.class);

        return this.userProfileCommentServices.save(userProfileComment, UserProfileCommentCreateReply.class);
    }

    @JmsValidate(model = UserProfileCommentDeleteByIdRequest.class)
    @JmsAfterReturn(message = COMMENT_DELETE_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.comments.user.profile.delete.by.id}", containerFactory = QUEUE)
    public EmptyReplyModel deleteById(Message message) {
        UserProfileCommentDeleteByIdRequest requestModel = this.jmsHelper.getResultModel(message, UserProfileCommentDeleteByIdRequest.class);

        this.userProfileCommentServices.deleteById(requestModel.getId());

        return new EmptyReplyModel();
    }

    @JmsValidate(model = UserProfileCommentsByUserProfileUsernameRequest.class)
    @JmsAfterReturn(message = COMMENT_DELETE_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.comments.user.profile.find.by.userProfileUsername}", containerFactory = QUEUE)
    public List<UserProfileCommentsByUserProfileUsernameRequest> allByUserProfileUsername(Message message) {
        UserProfileCommentsByUserProfileUsernameRequest requestModel = this.jmsHelper.getResultModel(message, UserProfileCommentsByUserProfileUsernameRequest.class);

        return this.userProfileCommentServices.findAllByUserProfileUsername(requestModel.getUserProfileUsername(), UserProfileCommentsByUserProfileUsernameRequest.class);
    }

}

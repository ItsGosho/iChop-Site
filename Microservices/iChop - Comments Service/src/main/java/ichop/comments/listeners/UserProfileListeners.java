package ichop.comments.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.domain.models.jms.UserProfileCommentReplyModel;
import ichop.comments.domain.models.jms.all.UserProfileCommentsByUserProfileUsernameRequest;
import ichop.comments.domain.models.jms.create.UserProfileCommentCreateRequest;
import ichop.comments.domain.models.service.UserProfileCommentServiceModel;
import ichop.comments.services.UserProfileCommentServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.util.List;

import static ichop.comments.constants.CommentReplyConstants.COMMENT_CREATED_SUCCESSFUL;
import static ichop.comments.constants.CommentReplyConstants.COMMENT_DELETE_SUCCESSFUL;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

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
    public UserProfileCommentReplyModel create(Message message) {
        UserProfileCommentCreateRequest requestModel = this.jmsHelper.toModel(message, UserProfileCommentCreateRequest.class);

        UserProfileCommentServiceModel userProfileComment = this.objectMapper.convertValue(requestModel, UserProfileCommentServiceModel.class);

        return this.userProfileCommentServices.save(userProfileComment, UserProfileCommentReplyModel.class);
    }

    @JmsValidate(model = UserProfileCommentsByUserProfileUsernameRequest.class)
    @JmsAfterReturn(message = COMMENT_DELETE_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.comments.user.profile.find.by.userProfileUsername}", containerFactory = QUEUE)
    public List<UserProfileCommentReplyModel> allByUserProfileUsername(Message message) {
        UserProfileCommentsByUserProfileUsernameRequest requestModel = this.jmsHelper.toModel(message, UserProfileCommentsByUserProfileUsernameRequest.class);

        return this.userProfileCommentServices.findAllByUserProfileUsername(requestModel.getUserProfileUsername(), UserProfileCommentReplyModel.class);
    }

}

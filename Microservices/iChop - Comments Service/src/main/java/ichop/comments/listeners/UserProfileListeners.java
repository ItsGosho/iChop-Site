package ichop.comments.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.common.aop.JmsAfterReturn;
import ichop.comments.common.aop.JmsValidate;
import ichop.comments.common.helpers.JmsHelper;
import ichop.comments.services.UserProfileCommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class UserProfileListeners {

    private final JmsHelper jmsHelper;
    private final ObjectMapper objectMapper;
    private final UserProfileCommentServices userProfileCommentServices;

    @Autowired
    public UserProfileListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, UserProfileCommentServices userProfileCommentServices) {
        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;
        this.userProfileCommentServices = userProfileCommentServices;
    }


    /*@JmsValidate(model = PasswordTokenCreateRequestModel.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.token.password.create}", containerFactory = "queueFactory")
    public PasswordTokenCreateReplyModel create(Message message) {
        PasswordTokenCreateRequestModel requestModel = this.jmsHelper.getResultModel(message, PasswordTokenCreateRequestModel.class);

        PasswordTokenServiceModel passwordToken = this.objectMapper.convertValue(requestModel, PasswordTokenServiceModel.class);

        PasswordTokenCreateReplyModel replyModel = this.passwordTokenServices.save(passwordToken, PasswordTokenCreateReplyModel.class);
        replyModel.setMessage(TOKEN_CREATED_SUCCESSFUL);

        return replyModel;
    }*/

}

package ichop.users.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.common.aop.JmsAfterReturn;
import ichop.users.common.aop.JmsValidate;
import ichop.users.common.helpers.BaseListener;
import ichop.users.common.helpers.JmsHelper;
import ichop.users.domain.models.jms.password.change.UserChangePasswordByTokenReply;
import ichop.users.domain.models.jms.password.change.UserChangePasswordByTokenRequest;
import ichop.users.domain.models.jms.password.change.UserChangePasswordReply;
import ichop.users.domain.models.jms.password.change.UserChangePasswordRequest;
import ichop.users.domain.models.jms.password.forgotten.UserForgottenPasswordReply;
import ichop.users.domain.models.jms.password.forgotten.UserForgottenPasswordRequest;
import ichop.users.domain.models.jms.register.UserRegisterReply;
import ichop.users.domain.models.jms.register.UserRegisterRequest;
import ichop.users.domain.models.jms.retrieve.UserFindByEmailReply;
import ichop.users.domain.models.jms.retrieve.UserFindByEmailRequest;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.services.RoleServices;
import ichop.users.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.users.common.constants.JmsFactories.QUEUE;
import static ichop.users.constants.UserReplyConstants.*;

@Component
public class UserListeners extends BaseListener {

    private final UserServices userServices;
    private final RoleServices roleServices;

    @Autowired
    protected UserListeners(JmsHelper jmsHelper,
                            ObjectMapper objectMapper,
                            UserServices userServices,
                            RoleServices roleServices) {
        super(jmsHelper, objectMapper);
        this.userServices = userServices;
        this.roleServices = roleServices;
    }


    @JmsValidate(model = UserRegisterRequest.class)
    @JmsAfterReturn(message = USER_REGISTER_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.authentication.register}", containerFactory = QUEUE)
    public UserRegisterReply register(Message message) {
        UserRegisterRequest requestModel = this.jmsHelper.getResultModel(message, UserRegisterRequest.class);

        UserServiceModel user = this.userServices.register(requestModel);

        return super.objectMapper.convertValue(user, UserRegisterReply.class);
    }

    @JmsValidate(model = UserFindByEmailRequest.class)
    @JmsAfterReturn(message = USER_FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.find.by.email}", containerFactory = QUEUE)
    public UserFindByEmailReply findByEmail(Message message) {
        UserFindByEmailRequest requestModel = this.jmsHelper.getResultModel(message, UserFindByEmailRequest.class);

        UserServiceModel user = this.userServices.findByEmail(requestModel.getEmail());

        UserFindByEmailReply reply = super.objectMapper.convertValue(user, UserFindByEmailReply.class);
        reply.setAuthority(this.roleServices.findHighestOfUser(user).getAuthority());
        return reply;
    }

    @JmsValidate(model = UserChangePasswordRequest.class)
    @JmsAfterReturn(message = USER_PASSWORD_CHANGED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.password.change}", containerFactory = QUEUE)
    public UserChangePasswordReply changePassword(Message message) {
        UserChangePasswordRequest requestModel = this.jmsHelper.getResultModel(message, UserChangePasswordRequest.class);

        this.userServices.changePassword(requestModel.getEmail(),requestModel.getPassword());

        return new UserChangePasswordReply();
    }

    @JmsValidate(model = UserChangePasswordByTokenRequest.class)
    @JmsAfterReturn(message = USER_PASSWORD_CHANGED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.password.change.by.token}", containerFactory = QUEUE)
    public UserChangePasswordByTokenReply changePasswordByToken(Message message) {
        UserChangePasswordByTokenRequest requestModel = this.jmsHelper.getResultModel(message, UserChangePasswordByTokenRequest.class);

        return null;
    }

    @JmsValidate(model = UserForgottenPasswordRequest.class)
    @JmsAfterReturn(message = USER_PASSWORD_TOKEN_SENT_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.forgotten.password}", containerFactory = QUEUE)
    public UserForgottenPasswordReply forgottenPassword(Message message) {
        UserForgottenPasswordRequest requestModel = this.jmsHelper.getResultModel(message, UserForgottenPasswordRequest.class);

        return null;
    }

}

package ichop.users.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.domain.models.jms.UserReply;
import ichop.users.domain.models.jms.email.EmailResetPasswordRequest;
import ichop.users.domain.models.jms.password.change.UserChangePasswordByTokenRequest;
import ichop.users.domain.models.jms.password.change.UserChangePasswordRequest;
import ichop.users.domain.models.jms.password.forgotten.UserForgottenPasswordRequest;
import ichop.users.domain.models.jms.register.UserRegisterRequest;
import ichop.users.domain.models.jms.retrieve.UserFindByEmailRequest;
import ichop.users.domain.models.jms.retrieve.UserFindByUsernameRequest;
import ichop.users.domain.models.jms.retrieve.UsersAllPageableRequest;
import ichop.users.domain.models.jms.token.PasswordTokenReply;
import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateRequest;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.requesters.EmailRequester;
import ichop.users.requesters.PasswordTokenRequester;
import ichop.users.services.RoleServices;
import ichop.users.services.UserServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.EmptyReply;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.util.List;

import static ichop.users.constants.UserReplyConstants.*;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

@Component
public class UserListeners extends BaseListener {

    private final UserServices userServices;
    private final RoleServices roleServices;
    private final EmailRequester emailRequester;
    private final PasswordTokenRequester passwordTokenRequester;

    @Autowired
    protected UserListeners(JmsHelper jmsHelper,
                            ObjectMapper objectMapper,
                            UserServices userServices,
                            RoleServices roleServices,
                            EmailRequester emailRequester,
                            PasswordTokenRequester passwordTokenRequester) {
        super(jmsHelper, objectMapper);
        this.userServices = userServices;
        this.roleServices = roleServices;
        this.emailRequester = emailRequester;
        this.passwordTokenRequester = passwordTokenRequester;
    }


    @JmsValidate(model = UserRegisterRequest.class)
    @JmsAfterReturn(message = REGISTER_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.authentication.register}", containerFactory = QUEUE)
    public UserReply register(Message message) {
        UserRegisterRequest requestModel = this.jmsHelper.toModel(message, UserRegisterRequest.class);

        UserServiceModel user = this.userServices.register(requestModel);

        return super.objectMapper.convertValue(user, UserReply.class);
    }

    @JmsValidate(model = UserFindByEmailRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.find.by.email}", containerFactory = QUEUE)
    public UserReply findByEmail(Message message) {
        UserFindByEmailRequest requestModel = this.jmsHelper.toModel(message, UserFindByEmailRequest.class);

        UserServiceModel user = this.userServices.findByEmail(requestModel.getEmail());

        UserReply reply = super.objectMapper.convertValue(user, UserReply.class);
        reply.setAuthority(this.roleServices.findHighestOfUser(user).getAuthority());
        return reply;
    }

    @JmsValidate(model = UserFindByUsernameRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.find.by.username}", containerFactory = QUEUE)
    public UserReply findByUsername(Message message) {
        UserFindByUsernameRequest requestModel = this.jmsHelper.toModel(message, UserFindByUsernameRequest.class);

        UserServiceModel user = this.userServices.findByUsername(requestModel.getUsername());

        UserReply reply = super.objectMapper.convertValue(user, UserReply.class);
        reply.setAuthority(this.roleServices.findHighestOfUser(user).getAuthority());
        return reply;
    }

    @JmsValidate(model = UsersAllPageableRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.find.all.pageable}", containerFactory = QUEUE)
    public List<UserReply> allPageable(Message message) {
        UsersAllPageableRequest requestModel = this.jmsHelper.toModel(message, UsersAllPageableRequest.class);

        List<UserServiceModel> users = this.userServices.findAll(requestModel.getPageable());

        return null;
    }

    @JmsValidate(model = UserChangePasswordRequest.class)
    @JmsAfterReturn(message = PASSWORD_CHANGED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.password.change}", containerFactory = QUEUE)
    public EmptyReply changePassword(Message message) {
        UserChangePasswordRequest requestModel = this.jmsHelper.toModel(message, UserChangePasswordRequest.class);

        this.userServices.changePassword(requestModel.getUsername(), requestModel.getPassword());

        return new EmptyReply();
    }

    @JmsValidate(model = UserChangePasswordByTokenRequest.class)
    @JmsAfterReturn(message = PASSWORD_CHANGED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.password.change.by.token}", containerFactory = QUEUE)
    public EmptyReply changePasswordByToken(Message message) {
        UserChangePasswordByTokenRequest requestModel = this.jmsHelper.toModel(message, UserChangePasswordByTokenRequest.class);

        JmsReplyModel findByTokenReply = this.passwordTokenRequester.findByToken(requestModel.getToken());
        PasswordTokenReply passwordToken = this.objectMapper.convertValue(findByTokenReply.getData(), PasswordTokenReply.class);

        UserServiceModel user = this.userServices.findByUsername(passwordToken.getUserUsername());

        this.userServices.changePassword(user.getUsername(), requestModel.getPassword());

        return new EmptyReply();
    }

    @JmsValidate(model = UserForgottenPasswordRequest.class)
    @JmsAfterReturn(message = PASSWORD_TOKEN_SENT_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.forgotten.password}", containerFactory = QUEUE)
    public EmptyReply forgottenPassword(Message message) {
        UserForgottenPasswordRequest requestModel = this.jmsHelper.toModel(message, UserForgottenPasswordRequest.class);

        UserServiceModel user = this.userServices.findByEmail(requestModel.getEmail());

        PasswordTokenCreateRequest passwordTokenCreateRequest = new PasswordTokenCreateRequest(user.getUsername());
        JmsReplyModel passwordTokenCreateReply = this.passwordTokenRequester.create(passwordTokenCreateRequest);
        PasswordTokenReply passwordToken = this.objectMapper.convertValue(passwordTokenCreateReply.getData(), PasswordTokenReply.class);

        EmailResetPasswordRequest emailResetPasswordRequest = new EmailResetPasswordRequest();
        emailResetPasswordRequest.setTo(user.getEmail());
        emailResetPasswordRequest.setToken(passwordToken.getToken());
        emailResetPasswordRequest.setExpirationDate(passwordToken.getCreationDate().plusHours(24));

        this.emailRequester.sendPasswordReset(emailResetPasswordRequest);

        return new EmptyReply();
    }

}

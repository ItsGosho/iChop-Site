package ichop.users.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.common.aop.JmsAfterReturn;
import ichop.users.common.aop.JmsValidate;
import ichop.users.common.helpers.BaseListener;
import ichop.users.common.helpers.JmsHelper;
import ichop.users.domain.models.jms.email.EmailResetPasswordRequest;
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
import ichop.users.domain.models.jms.retrieve.UsersAllPageableReply;
import ichop.users.domain.models.jms.retrieve.UsersAllPageableRequest;
import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateReply;
import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateRequest;
import ichop.users.domain.models.jms.token.retrieve.password.PasswordTokenFindByTokenRequest;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.requesters.EmailRequester;
import ichop.users.requesters.PasswordTokenRequester;
import ichop.users.services.RoleServices;
import ichop.users.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import java.util.List;

import static ichop.users.common.constants.JmsFactories.QUEUE;
import static ichop.users.constants.UserReplyConstants.*;

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
    public UserRegisterReply register(Message message) {
        UserRegisterRequest requestModel = this.jmsHelper.getResultModel(message, UserRegisterRequest.class);

        UserServiceModel user = this.userServices.register(requestModel);

        return super.objectMapper.convertValue(user, UserRegisterReply.class);
    }

    @JmsValidate(model = UserFindByEmailRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.find.by.email}", containerFactory = QUEUE)
    public UserFindByEmailReply findByEmail(Message message) {
        UserFindByEmailRequest requestModel = this.jmsHelper.getResultModel(message, UserFindByEmailRequest.class);

        UserServiceModel user = this.userServices.findByEmail(requestModel.getEmail());

        UserFindByEmailReply reply = super.objectMapper.convertValue(user, UserFindByEmailReply.class);
        reply.setAuthority(this.roleServices.findHighestOfUser(user).getAuthority());
        return reply;
    }

    @JmsValidate(model = UsersAllPageableRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.find.all.pageable}", containerFactory = QUEUE)
    public UsersAllPageableReply allPageable(Message message) {
        UsersAllPageableRequest requestModel = this.jmsHelper.getResultModel(message, UsersAllPageableRequest.class);

        List<UserServiceModel> users = this.userServices.findAll(requestModel.getPageable());

        return new UsersAllPageableReply(users);
    }

    @JmsValidate(model = UserChangePasswordRequest.class)
    @JmsAfterReturn(message = PASSWORD_CHANGED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.password.change}", containerFactory = QUEUE)
    public UserChangePasswordReply changePassword(Message message) {
        UserChangePasswordRequest requestModel = this.jmsHelper.getResultModel(message, UserChangePasswordRequest.class);

        this.userServices.changePassword(requestModel.getEmail(), requestModel.getPassword());

        return new UserChangePasswordReply();
    }

    @JmsValidate(model = UserChangePasswordByTokenRequest.class)
    @JmsAfterReturn(message = PASSWORD_CHANGED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.password.change.by.token}", containerFactory = QUEUE)
    public UserChangePasswordByTokenReply changePasswordByToken(Message message) {
        UserChangePasswordByTokenRequest requestModel = this.jmsHelper.getResultModel(message, UserChangePasswordByTokenRequest.class);

        String userId = this.passwordTokenRequester.findByToken(new PasswordTokenFindByTokenRequest(requestModel.getToken())).getUserId();
        UserServiceModel user = this.userServices.findById(userId);

        this.userServices.changePassword(user.getEmail(), requestModel.getPassword());

        return new UserChangePasswordByTokenReply();
    }

    @JmsValidate(model = UserForgottenPasswordRequest.class)
    @JmsAfterReturn(message = PASSWORD_TOKEN_SENT_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.forgotten.password}", containerFactory = QUEUE)
    public UserForgottenPasswordReply forgottenPassword(Message message) {
        UserForgottenPasswordRequest requestModel = this.jmsHelper.getResultModel(message, UserForgottenPasswordRequest.class);

        UserServiceModel user = this.userServices.findByEmail(requestModel.getEmail());

        PasswordTokenCreateRequest passwordTokenCreateRequest = new PasswordTokenCreateRequest(user.getId());
        PasswordTokenCreateReply passwordTokenCreateReply = this.passwordTokenRequester.create(passwordTokenCreateRequest);

        EmailResetPasswordRequest emailResetPasswordRequest = new EmailResetPasswordRequest();
        emailResetPasswordRequest.setTo(user.getEmail());
        emailResetPasswordRequest.setToken(passwordTokenCreateReply.getToken());
        emailResetPasswordRequest.setExpirationDate(passwordTokenCreateReply.getCreationDate().plusHours(24));

        this.emailRequester.sendPasswordReset(emailResetPasswordRequest);

        return new UserForgottenPasswordReply();
    }

}

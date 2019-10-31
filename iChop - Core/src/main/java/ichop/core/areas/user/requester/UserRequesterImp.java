package ichop.core.areas.user.requester;

import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenReply;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenRequest;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordReply;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordRequest;
import ichop.core.areas.user.models.jms.password.forgotten.UserForgottenPasswordReply;
import ichop.core.areas.user.models.jms.password.forgotten.UserForgottenPasswordRequest;
import ichop.core.areas.user.models.jms.register.UserRegisterReply;
import ichop.core.areas.user.models.jms.register.UserRegisterRequest;
import ichop.core.areas.user.models.jms.retrieve.UserFindByEmailReply;
import ichop.core.areas.user.models.jms.retrieve.UserFindByEmailRequest;
import ichop.core.areas.user.models.jms.retrieve.UsersAllPageableReply;
import ichop.core.areas.user.models.jms.retrieve.UsersAllPageableRequest;
import ichop.core.common.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class UserRequesterImp implements UserRequester {

    private final JmsHelper jmsHelper;

    private String findByEmailDestination;
    private String registerDestination;

    private String changePasswordDestination;
    private String changePasswordByTokenDestination;
    private String findAllPageableDestination;
    private String forgottenPasswordDestination;

    @Autowired
    public UserRequesterImp(JmsHelper jmsHelper,
                            @Value("${artemis.queue.users.find.by.email}") String findByEmailDestination,
                            @Value("${artemis.queue.users.authentication.register}") String registerDestination,
                            @Value("${artemis.queue.users.password.change}") String changePasswordDestination,
                            @Value("${artemis.queue.users.password.change.by.token}") String changePasswordByTokenDestination,
                            @Value("${artemis.queue.users.find.all.pageable}") String findAllPageableDestination,
                            @Value("${artemis.queue.users.forgotten.password}") String forgottenPasswordDestination) {
        this.jmsHelper = jmsHelper;

        this.findByEmailDestination = findByEmailDestination;
        this.registerDestination = registerDestination;
        this.changePasswordDestination = changePasswordDestination;
        this.changePasswordByTokenDestination = changePasswordByTokenDestination;
        this.findAllPageableDestination = findAllPageableDestination;
        this.forgottenPasswordDestination = forgottenPasswordDestination;
    }

    @Override
    public UserFindByEmailReply findByEmail(String email) {
        UserFindByEmailRequest request = new UserFindByEmailRequest(email);

        return this.jmsHelper.sendAndReceive(this.findByEmailDestination, request, UserFindByEmailReply.class);
    }

    @Override
    public UserRegisterReply register(UserRegisterRequest request) {
        return this.jmsHelper.sendAndReceive(this.registerDestination, request, UserRegisterReply.class);
    }

    @Override
    public UserChangePasswordReply changePassword(UserChangePasswordRequest request) {
        return this.jmsHelper.sendAndReceive(this.changePasswordDestination, request, UserChangePasswordReply.class);
    }

    @Override
    public UserChangePasswordByTokenReply changePasswordByToken(UserChangePasswordByTokenRequest request) {
        return this.jmsHelper.sendAndReceive(this.changePasswordByTokenDestination, request, UserChangePasswordByTokenReply.class);
    }

    @Override
    public UserForgottenPasswordReply forgottenPassword(UserForgottenPasswordRequest request) {
        return this.jmsHelper.sendAndReceive(this.forgottenPasswordDestination, request, UserForgottenPasswordReply.class);
    }

    @Override
    public UsersAllPageableReply findAllPageable(Pageable pageable) {
        UsersAllPageableRequest request = new UsersAllPageableRequest();
        request.setPageable(pageable);

        return this.jmsHelper.sendAndReceive(this.findAllPageableDestination, request, UsersAllPageableReply.class);
    }

}

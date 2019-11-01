package ichop.core.areas.user.requester;

import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenReply;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenRequest;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordReply;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordRequest;
import ichop.core.areas.user.models.jms.password.forgotten.UserForgottenPasswordReply;
import ichop.core.areas.user.models.jms.password.forgotten.UserForgottenPasswordRequest;
import ichop.core.areas.user.models.jms.register.UserRegisterReply;
import ichop.core.areas.user.models.jms.register.UserRegisterRequest;
import ichop.core.areas.user.models.jms.retrieve.*;
import ichop.core.areas.user.models.jms.role.*;
import ichop.core.common.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class UserRequesterImp implements UserRequester {

    private final JmsHelper jmsHelper;

    private String findByEmailDestination;
    private String findByUsernameDestination;
    private String registerDestination;

    private String changePasswordDestination;
    private String changePasswordByTokenDestination;
    private String findAllPageableDestination;
    private String forgottenPasswordDestination;

    private String promoteDestination;
    private String demoteDestination;
    private String hasNextRoleDestination;
    private String hasPreviousRoleDestination;

    @Autowired
    public UserRequesterImp(JmsHelper jmsHelper,
                            @Value("${artemis.queue.users.find.by.email}") String findByEmailDestination,
                            @Value("${artemis.queue.users.find.by.username}") String findByUsernameDestination,
                            @Value("${artemis.queue.users.authentication.register}") String registerDestination,
                            @Value("${artemis.queue.users.password.change}") String changePasswordDestination,
                            @Value("${artemis.queue.users.password.change.by.token}") String changePasswordByTokenDestination,
                            @Value("${artemis.queue.users.find.all.pageable}") String findAllPageableDestination,
                            @Value("${artemis.queue.users.forgotten.password}") String forgottenPasswordDestination,
                            @Value("${artemis.queue.users.role.promote}") String promoteDestination,
                            @Value("${artemis.queue.users.role.demote}") String demoteDestination,
                            @Value("${artemis.queue.users.role.has.next}") String hasNextRoleDestination,
                            @Value("${artemis.queue.users.role.has.previous}") String hasPreviousRoleDestination) {
        this.jmsHelper = jmsHelper;

        this.findByEmailDestination = findByEmailDestination;
        this.findByUsernameDestination = findByUsernameDestination;
        this.registerDestination = registerDestination;
        this.changePasswordDestination = changePasswordDestination;
        this.changePasswordByTokenDestination = changePasswordByTokenDestination;
        this.findAllPageableDestination = findAllPageableDestination;
        this.forgottenPasswordDestination = forgottenPasswordDestination;

        this.promoteDestination = promoteDestination;
        this.demoteDestination = demoteDestination;
        this.hasNextRoleDestination = hasNextRoleDestination;
        this.hasPreviousRoleDestination = hasPreviousRoleDestination;
    }

    @Override
    public UserFindByEmailReply findByEmail(String email) {
        UserFindByEmailRequest request = new UserFindByEmailRequest(email);

        return this.jmsHelper.sendAndReceive(this.findByEmailDestination, request, UserFindByEmailReply.class);
    }

    @Override
    public UserFindByUsernameReply findByUsername(String username) {
        UserFindByUsernameRequest request = new UserFindByUsernameRequest(username);

        return this.jmsHelper.sendAndReceive(this.findByUsernameDestination, request, UserFindByUsernameReply.class);
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

    @Override
    public UserRolePromoteReply promote(String username) {
        UserRolePromoteRequest userRolePromoteRequest = new UserRolePromoteRequest(username);

        return this.jmsHelper.sendAndReceive(this.promoteDestination, userRolePromoteRequest, UserRolePromoteReply.class);
    }

    @Override
    public UserRoleDemoteReply demote(String username) {
        UserRoleDemoteRequest userRoleDemoteRequest = new UserRoleDemoteRequest(username);

        return this.jmsHelper.sendAndReceive(this.demoteDestination, userRoleDemoteRequest, UserRoleDemoteReply.class);
    }

    @Override
    public UserHasNextRoleReply hasNextRole(String username) {
        UserHasNextRoleRequest userHasNextRoleRequest = new UserHasNextRoleRequest(username);

        return this.jmsHelper.sendAndReceive(this.hasNextRoleDestination, userHasNextRoleRequest, UserHasNextRoleReply.class);
    }

    @Override
    public UserHasPreviousRoleReply hasPreviousRole(String username) {
        UserHasPreviousRoleRequest userHasPreviousRoleRequest = new UserHasPreviousRoleRequest(username);

        return this.jmsHelper.sendAndReceive(this.hasPreviousRoleDestination, userHasPreviousRoleRequest, UserHasPreviousRoleReply.class);
    }

}

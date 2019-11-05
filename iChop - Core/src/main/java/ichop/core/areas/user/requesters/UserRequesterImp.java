package ichop.core.areas.user.requesters;

import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenRequest;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordRequest;
import ichop.core.areas.user.models.jms.password.forgotten.UserForgottenPasswordRequest;
import ichop.core.areas.user.models.jms.register.UserRegisterRequest;
import ichop.core.areas.user.models.jms.retrieve.UserFindByEmailRequest;
import ichop.core.areas.user.models.jms.retrieve.UserFindByUsernameRequest;
import ichop.core.areas.user.models.jms.role.UserHasNextRoleRequest;
import ichop.core.areas.user.models.jms.role.UserHasPreviousRoleRequest;
import ichop.core.areas.user.models.jms.role.UserRoleDemoteRequest;
import ichop.core.areas.user.models.jms.role.UserRolePromoteRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserRequesterImp implements UserRequester {

    private final JmsHelper jmsHelper;

    private final String findByEmailDestination;
    private final String findByUsernameDestination;
    private final String registerDestination;

    private final String changePasswordDestination;
    private final String changePasswordByTokenDestination;
    private final String findAllPageableDestination;
    private final String forgottenPasswordDestination;

    private final String promoteDestination;
    private final String demoteDestination;
    private final String hasNextRoleDestination;
    private final String hasPreviousRoleDestination;

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
    public JmsReplyModel findByEmail(String email) {
        UserFindByEmailRequest request = new UserFindByEmailRequest(email);

        return this.jmsHelper.sendAndReceive(this.findByEmailDestination, request);
    }

    @Override
    public JmsReplyModel findByUsername(String username) {
        UserFindByUsernameRequest request = new UserFindByUsernameRequest(username);

        return this.jmsHelper.sendAndReceive(this.findByUsernameDestination, request);
    }

    @Override
    public JmsReplyModel register(UserRegisterRequest request) {
        return this.jmsHelper.sendAndReceive(this.registerDestination, request);
    }

    @Override
    public JmsReplyModel changePassword(UserChangePasswordRequest request) {
        return this.jmsHelper.sendAndReceive(this.changePasswordDestination, request);
    }

    @Override
    public JmsReplyModel changePasswordByToken(UserChangePasswordByTokenRequest request) {
        return this.jmsHelper.sendAndReceive(this.changePasswordByTokenDestination, request);
    }

    @Override
    public JmsReplyModel forgottenPassword(UserForgottenPasswordRequest request) {
        return this.jmsHelper.sendAndReceive(this.forgottenPasswordDestination, request);
    }

    /*@Override
    public JmsReplyModel findAllPageable(Pageable pageable) {
        UsersAllPageableRequest request = new UsersAllPageableRequest();
        request.setPageable(pageable);

        return this.jmsHelper.sendAndReceive(this.findAllPageableDestination, request);
    }*/

    @Override
    public JmsReplyModel promote(String username) {
        UserRolePromoteRequest userRolePromoteRequest = new UserRolePromoteRequest(username);

        return this.jmsHelper.sendAndReceive(this.promoteDestination, userRolePromoteRequest);
    }

    @Override
    public JmsReplyModel demote(String username) {
        UserRoleDemoteRequest userRoleDemoteRequest = new UserRoleDemoteRequest(username);

        return this.jmsHelper.sendAndReceive(this.demoteDestination, userRoleDemoteRequest);
    }

    @Override
    public JmsReplyModel hasNextRole(String username) {
        UserHasNextRoleRequest userHasNextRoleRequest = new UserHasNextRoleRequest(username);

        return this.jmsHelper.sendAndReceive(this.hasNextRoleDestination, userHasNextRoleRequest);
    }

    @Override
    public JmsReplyModel hasPreviousRole(String username) {
        UserHasPreviousRoleRequest userHasPreviousRoleRequest = new UserHasPreviousRoleRequest(username);

        return this.jmsHelper.sendAndReceive(this.hasPreviousRoleDestination, userHasPreviousRoleRequest);
    }

}

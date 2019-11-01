package ichop.users.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.common.aop.JmsAfterReturn;
import ichop.users.common.aop.JmsValidate;
import ichop.users.common.helpers.BaseListener;
import ichop.users.common.helpers.JmsHelper;
import ichop.users.domain.models.jms.role.*;
import ichop.users.services.RoleServices;
import ichop.users.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.users.common.constants.JmsFactories.QUEUE;
import static ichop.users.constants.UserReplyConstants.*;

@Component
public class UserRoleListeners extends BaseListener {

    private final UserServices userServices;
    private final RoleServices roleServices;

    @Autowired
    protected UserRoleListeners(JmsHelper jmsHelper,
                                ObjectMapper objectMapper,
                                UserServices userServices,
                                RoleServices roleServices) {
        super(jmsHelper, objectMapper);
        this.userServices = userServices;
        this.roleServices = roleServices;
    }


    @JmsValidate(model = UserRolePromoteRequest.class)
    @JmsAfterReturn(message = PROMOTE_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.role.promote}", containerFactory = QUEUE)
    public UserRolePromoteReply promote(Message message) {
        UserRolePromoteRequest requestModel = this.jmsHelper.getResultModel(message, UserRolePromoteRequest.class);

        this.userServices.promote(requestModel.getUsername());

        return new UserRolePromoteReply();
    }

    @JmsValidate(model = UserRoleDemoteRequest.class)
    @JmsAfterReturn(message = DEMOTE_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.role.demote}", containerFactory = QUEUE)
    public UserRoleDemoteReply demote(Message message) {
        UserRoleDemoteRequest requestModel = this.jmsHelper.getResultModel(message, UserRoleDemoteRequest.class);

        this.userServices.demote(requestModel.getUsername());

        return new UserRoleDemoteReply();
    }

    @JmsValidate(model = UserHasNextRoleRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.role.has.next}", containerFactory = QUEUE)
    public UserHasNextRoleReply hasNext(Message message) {
        UserHasNextRoleRequest requestModel = this.jmsHelper.getResultModel(message, UserHasNextRoleRequest.class);

        boolean hasNextRole = this.userServices.hasNextRole(requestModel.getUsername());

        return new UserHasNextRoleReply(hasNextRole);
    }

    @JmsValidate(model = UserHasPreviousRoleRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.role.has.previous}", containerFactory = QUEUE)
    public UserHasPreviousRoleReply hasPrevious(Message message) {
        UserHasPreviousRoleRequest requestModel = this.jmsHelper.getResultModel(message, UserHasPreviousRoleRequest.class);

        boolean hasPreviousRole = this.userServices.hasPreviousRole(requestModel.getUsername());

        return new UserHasPreviousRoleReply(hasPreviousRole);
    }

}

package ichop.users.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.domain.models.jms.role.UserHasNextRoleRequest;
import ichop.users.domain.models.jms.role.UserHasPreviousRoleRequest;
import ichop.users.domain.models.jms.role.UserRoleDemoteRequest;
import ichop.users.domain.models.jms.role.UserRolePromoteRequest;
import ichop.users.services.UserServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.domain.EmptyReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.users.constants.UserReplyConstants.*;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

@Component
public class UserRoleListeners extends BaseListener {

    private final UserServices userServices;

    @Autowired
    protected UserRoleListeners(JmsHelper jmsHelper,
                                ObjectMapper objectMapper,
                                UserServices userServices) {
        super(jmsHelper, objectMapper);
        this.userServices = userServices;
    }


    @JmsValidate(model = UserRolePromoteRequest.class)
    @JmsAfterReturn(message = PROMOTE_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.role.promote}", containerFactory = QUEUE)
    public EmptyReply promote(Message message) {
        UserRolePromoteRequest requestModel = this.jmsHelper.toModel(message, UserRolePromoteRequest.class);

        this.userServices.promote(requestModel.getUsername());

        return new EmptyReply();
    }

    @JmsValidate(model = UserRoleDemoteRequest.class)
    @JmsAfterReturn(message = DEMOTE_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.role.demote}", containerFactory = QUEUE)
    public EmptyReply demote(Message message) {
        UserRoleDemoteRequest requestModel = this.jmsHelper.toModel(message, UserRoleDemoteRequest.class);

        this.userServices.demote(requestModel.getUsername());

        return new EmptyReply();
    }

    @JmsValidate(model = UserHasNextRoleRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.role.has.next}", containerFactory = QUEUE)
    public BoolReply hasNext(Message message) {
        UserHasNextRoleRequest requestModel = this.jmsHelper.toModel(message, UserHasNextRoleRequest.class);

        boolean hasNextRole = this.userServices.hasNextRole(requestModel.getUsername());

        return new BoolReply(hasNextRole);
    }

    @JmsValidate(model = UserHasPreviousRoleRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.role.has.previous}", containerFactory = QUEUE)
    public BoolReply hasPrevious(Message message) {
        UserHasPreviousRoleRequest requestModel = this.jmsHelper.toModel(message, UserHasPreviousRoleRequest.class);

        boolean hasPreviousRole = this.userServices.hasPreviousRole(requestModel.getUsername());

        return new BoolReply(hasPreviousRole);
    }

}

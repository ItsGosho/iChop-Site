package ichop.users.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.common.aop.JmsAfterReturn;
import ichop.users.common.aop.JmsValidate;
import ichop.users.common.helpers.BaseListener;
import ichop.users.common.helpers.JmsHelper;
import ichop.users.domain.models.jms.register.UserRegisterReply;
import ichop.users.domain.models.jms.register.UserRegisterRequest;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.services.UserFollowServices;
import ichop.users.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.users.common.constants.JmsFactories.QUEUE;

@Component
public class UsersInformationListeners extends BaseListener {

    private final UserServices userServices;
    private final UserFollowServices userFollowServices;

    @Autowired
    protected UsersInformationListeners(JmsHelper jmsHelper,
                                        ObjectMapper objectMapper,
                                        UserServices userServices,
                                        UserFollowServices userFollowServices) {
        super(jmsHelper, objectMapper);
        this.userServices = userServices;
        this.userFollowServices = userFollowServices;
    }


    @JmsValidate(model = UserRegisterRequest.class)
    @JmsAfterReturn(message = USER_REGISTER_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.information.update}", containerFactory = QUEUE)
    public UserRegisterReply register(Message message) {
        UserRegisterRequest requestModel = this.jmsHelper.getResultModel(message, UserRegisterRequest.class);

        UserServiceModel user = this.userServices.register(requestModel);

        return super.objectMapper.convertValue(user, UserRegisterReply.class);
    }
}

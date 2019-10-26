package ichop.users.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.api.FeatureConstructor;
import ichop.users.common.aop.JmsAfterReturn;
import ichop.users.common.aop.JmsValidate;
import ichop.users.common.helpers.BaseListener;
import ichop.users.common.helpers.JmsHelper;
import ichop.users.domain.models.jms.register.UserRegisterReply;
import ichop.users.domain.models.jms.register.UserRegisterRequest;
import ichop.users.domain.models.jms.retrieve.UserFindByEmailReply;
import ichop.users.domain.models.jms.retrieve.UserFindByEmailRequest;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.services.UserServices;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.users.common.constants.JmsFactories.QUEUE;
import static ichop.users.constants.UserReplyConstants.USER_FETCHED_SUCCESSFUL;
import static ichop.users.constants.UserReplyConstants.USER_REGISTER_SUCCESSFUL;

@Component
public class UserListeners extends BaseListener {

    private final UserServices userServices;

    @FeatureConstructor
    protected UserListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, UserServices userServices) {
        super(jmsHelper, objectMapper);
        this.userServices = userServices;
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

        UserServiceModel user = this.userServices.findUserByEmail(requestModel.getEmail());

        return super.objectMapper.convertValue(user, UserFindByEmailReply.class);
    }

}

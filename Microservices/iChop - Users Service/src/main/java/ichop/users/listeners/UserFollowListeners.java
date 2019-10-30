package ichop.users.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.common.aop.JmsAfterReturn;
import ichop.users.common.aop.JmsValidate;
import ichop.users.common.helpers.BaseListener;
import ichop.users.common.helpers.JmsHelper;
import ichop.users.domain.models.jms.information.UserInformationRetrieveReply;
import ichop.users.domain.models.jms.information.UserInformationRetrieveRequest;
import ichop.users.domain.models.jms.information.UserInformationUpdateReply;
import ichop.users.domain.models.jms.information.UserInformationUpdateRequest;
import ichop.users.services.UserFollowServices;
import ichop.users.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.users.common.constants.JmsFactories.QUEUE;
import static ichop.users.constants.UserReplyConstants.FETCHED_SUCCESSFUL;
import static ichop.users.constants.UserReplyConstants.INFORMATION_UPDATED_SUCCESSFUL;

@Component
public class UserFollowListeners extends BaseListener {

    private final UserServices userServices;
    private final UserFollowServices userFollowServices;

    @Autowired
    protected UserFollowListeners(JmsHelper jmsHelper,
                                  ObjectMapper objectMapper,
                                  UserServices userServices,
                                  UserFollowServices userFollowServices) {
        super(jmsHelper, objectMapper);
        this.userServices = userServices;
        this.userFollowServices = userFollowServices;
    }


    @JmsValidate(model = UserInformationUpdateRequest.class)
    @JmsAfterReturn(message = INFORMATION_UPDATED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.information.update}", containerFactory = QUEUE)
    public UserInformationUpdateReply update(Message message) {
        UserInformationUpdateRequest requestModel = this.jmsHelper.getResultModel(message, UserInformationUpdateRequest.class);


        return null;
    }

    @JmsValidate(model = UserInformationRetrieveRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.information.retrieve}", containerFactory = QUEUE)
    public UserInformationRetrieveReply retrieve(Message message) {
        UserInformationRetrieveRequest requestModel = this.jmsHelper.getResultModel(message, UserInformationRetrieveRequest.class);


        return null;
    }

}

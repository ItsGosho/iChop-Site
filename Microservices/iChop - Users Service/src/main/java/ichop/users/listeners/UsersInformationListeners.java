package ichop.users.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.common.aop.JmsAfterReturn;
import ichop.users.common.aop.JmsValidate;
import ichop.users.common.helpers.BaseListener;
import ichop.users.common.helpers.JmsHelper;
import ichop.users.domain.models.jms.information.UserInformationRetrieveRequest;
import ichop.users.domain.models.jms.information.UserInformationUpdateRequest;
import ichop.users.domain.models.service.UserInformationServiceModel;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.services.UserInformationServices;
import ichop.users.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.users.common.constants.JmsFactories.QUEUE;
import static ichop.users.constants.UserReplyConstants.FETCHED_SUCCESSFUL;
import static ichop.users.constants.UserReplyConstants.INFORMATION_UPDATED_SUCCESSFUL;

@Component
public class UsersInformationListeners extends BaseListener {

    private final UserServices userServices;
    private final UserInformationServices userInformationServices;

    @Autowired
    protected UsersInformationListeners(JmsHelper jmsHelper,
                                        ObjectMapper objectMapper,
                                        UserServices userServices,
                                        UserInformationServices userInformationServices) {
        super(jmsHelper, objectMapper);
        this.userServices = userServices;
        this.userInformationServices = userInformationServices;
    }


    @JmsValidate(model = UserInformationUpdateRequest.class)
    @JmsAfterReturn(message = INFORMATION_UPDATED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.information.update}", containerFactory = QUEUE)
    public UserInformationUpdateReply update(Message message) {
        UserInformationUpdateRequest requestModel = this.jmsHelper.getResultModel(message, UserInformationUpdateRequest.class);

        /*TODO: update avatar!*/
        UserServiceModel user = this.userServices.findByUsername(requestModel.getUsername());

        UserInformationServiceModel information = this.userInformationServices.update(requestModel);
        return super.objectMapper.convertValue(information, UserInformationUpdateReply.class);
    }

    @JmsValidate(model = UserInformationRetrieveRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.information.retrieve}", containerFactory = QUEUE)
    public UserInformationRetrieveReply retrieve(Message message) {
        UserInformationRetrieveRequest requestModel = this.jmsHelper.getResultModel(message, UserInformationRetrieveRequest.class);

        UserInformationServiceModel information = this.userInformationServices.getByUser(requestModel.getUsername());

        return information != null ? super.objectMapper.convertValue(information, UserInformationRetrieveReply.class) : new UserInformationRetrieveReply();
    }

}

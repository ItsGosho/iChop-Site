package ichop.core.areas.user.requester;

import ichop.core.areas.user.models.jms.information.UserInformationRetrieveReply;
import ichop.core.areas.user.models.jms.information.UserInformationRetrieveRequest;
import ichop.core.areas.user.models.jms.information.UserInformationUpdateReply;
import ichop.core.areas.user.models.jms.information.UserInformationUpdateRequest;
import ichop.core.common.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserInformationRequesterImp implements UserInformationRequester {

    private final JmsHelper jmsHelper;

    private String updateDestination;
    private String retrieveDestination;

    @Autowired
    public UserInformationRequesterImp(JmsHelper jmsHelper,
                                       @Value("${artemis.queue.users.information.update}") String updateDestination,
                                       @Value("${artemis.queue.users.information.retrieve}") String retrieveDestination) {
        this.jmsHelper = jmsHelper;

        this.updateDestination = updateDestination;
        this.retrieveDestination = retrieveDestination;
    }

    @Override
    public UserInformationUpdateReply update(UserInformationUpdateRequest request) {
        return this.jmsHelper.sendAndReceive(this.updateDestination, request, UserInformationUpdateReply.class);
    }

    @Override
    public UserInformationRetrieveReply retrieve(String username) {
        UserInformationRetrieveRequest request = new UserInformationRetrieveRequest(username);

        return this.jmsHelper.sendAndReceive(this.retrieveDestination, request, UserInformationRetrieveReply.class);
    }


}

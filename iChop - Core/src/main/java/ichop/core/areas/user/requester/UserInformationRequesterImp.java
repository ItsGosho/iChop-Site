package ichop.core.areas.user.requester;

import ichop.core.areas.user.models.jms.information.UserInformationRetrieveRequest;
import ichop.core.areas.user.models.jms.information.UserInformationUpdateRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserInformationRequesterImp implements UserInformationRequester {

    private final JmsHelper jmsHelper;

    private final String updateDestination;
    private final String retrieveDestination;

    @Autowired
    public UserInformationRequesterImp(JmsHelper jmsHelper,
                                       @Value("${artemis.queue.users.information.update}") String updateDestination,
                                       @Value("${artemis.queue.users.information.retrieve}") String retrieveDestination) {
        this.jmsHelper = jmsHelper;

        this.updateDestination = updateDestination;
        this.retrieveDestination = retrieveDestination;
    }

    @Override
    public JmsReplyModel update(UserInformationUpdateRequest request) {
        return this.jmsHelper.sendAndReceive(this.updateDestination, request);
    }

    @Override
    public JmsReplyModel retrieve(String username) {
        UserInformationRetrieveRequest request = new UserInformationRetrieveRequest(username);

        return this.jmsHelper.sendAndReceive(this.retrieveDestination, request);
    }


}

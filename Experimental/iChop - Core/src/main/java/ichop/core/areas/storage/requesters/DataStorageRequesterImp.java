package ichop.core.areas.storage.requesters;

import ichop.core.areas.storage.models.jms.UserSetAvatarRequest;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataStorageRequesterImp implements DataStorageRequester {

    private final JmsHelper jmsHelper;

    private final String setUserAvatarDestination;

    @Autowired
    public DataStorageRequesterImp(JmsHelper jmsHelper,
                                   @Value("${artemis.queue.data_storage.set.user.avatar}") String setUserAvatarDestination) {
        this.jmsHelper = jmsHelper;

        this.setUserAvatarDestination = setUserAvatarDestination;
    }

    @Override
    public void setUserAvatar(UserSetAvatarRequest request) {
       this.jmsHelper.send(this.setUserAvatarDestination, request);
    }

}

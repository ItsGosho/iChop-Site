package ichop.core.areas.user.requesters;

import ichop.core.areas.user.models.jms.information.UserInformationUpdateRequest;
import org.ichop.commons.domain.JmsReplyModel;

public interface UserInformationRequester {

    JmsReplyModel update(UserInformationUpdateRequest request);

    JmsReplyModel retrieve(String username);
}

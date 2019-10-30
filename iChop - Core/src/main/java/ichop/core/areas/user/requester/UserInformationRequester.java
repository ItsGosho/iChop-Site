package ichop.core.areas.user.requester;

import ichop.core.areas.user.models.jms.information.UserInformationRetrieveReply;
import ichop.core.areas.user.models.jms.information.UserInformationUpdateReply;
import ichop.core.areas.user.models.jms.information.UserInformationUpdateRequest;

public interface UserInformationRequester {

    UserInformationUpdateReply update(UserInformationUpdateRequest request);

    UserInformationRetrieveReply retrieve(String username);
}

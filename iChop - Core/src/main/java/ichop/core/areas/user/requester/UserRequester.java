package ichop.core.areas.user.requester;

import ichop.core.areas.user.models.jms.register.UserRegisterReply;
import ichop.core.areas.user.models.jms.register.UserRegisterRequest;
import ichop.core.areas.user.models.jms.retrieve.UserFindByEmailReply;

public interface UserRequester {
    UserFindByEmailReply findByEmail(String email);

    UserRegisterReply register(UserRegisterRequest request);
}
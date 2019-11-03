package ichop.core.areas.user.requester;

import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenRequest;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordRequest;
import ichop.core.areas.user.models.jms.password.forgotten.UserForgottenPasswordRequest;
import ichop.core.areas.user.models.jms.register.UserRegisterRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.data.domain.Pageable;

public interface UserRequester {

    JmsReplyModel findByEmail(String email);

    JmsReplyModel findByUsername(String username);

    JmsReplyModel register(UserRegisterRequest request);

    JmsReplyModel changePassword(UserChangePasswordRequest request);

    JmsReplyModel changePasswordByToken(UserChangePasswordByTokenRequest request);

    JmsReplyModel forgottenPassword(UserForgottenPasswordRequest request);

    JmsReplyModel findAllPageable(Pageable pageable);

    JmsReplyModel promote(String username);

    JmsReplyModel demote(String username);

    JmsReplyModel hasNextRole(String username);

    JmsReplyModel hasPreviousRole(String username);
}

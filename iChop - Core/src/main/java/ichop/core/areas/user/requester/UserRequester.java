package ichop.core.areas.user.requester;

import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenReply;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenRequest;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordReply;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordRequest;
import ichop.core.areas.user.models.jms.password.forgotten.UserForgottenPasswordReply;
import ichop.core.areas.user.models.jms.password.forgotten.UserForgottenPasswordRequest;
import ichop.core.areas.user.models.jms.register.UserRegisterReply;
import ichop.core.areas.user.models.jms.register.UserRegisterRequest;
import ichop.core.areas.user.models.jms.retrieve.UserFindByEmailReply;
import ichop.core.areas.user.models.jms.retrieve.UsersAllPageableReply;
import ichop.core.areas.user.models.jms.role.UserHasNextRoleReply;
import ichop.core.areas.user.models.jms.role.UserHasPreviousRoleReply;
import ichop.core.areas.user.models.jms.role.UserRoleDemoteReply;
import ichop.core.areas.user.models.jms.role.UserRolePromoteReply;
import org.springframework.data.domain.Pageable;

public interface UserRequester {
    UserFindByEmailReply findByEmail(String email);

    UserRegisterReply register(UserRegisterRequest request);

    UserChangePasswordReply changePassword(UserChangePasswordRequest request);

    UserChangePasswordByTokenReply changePasswordByToken(UserChangePasswordByTokenRequest request);

    UserForgottenPasswordReply forgottenPassword(UserForgottenPasswordRequest request);

    UsersAllPageableReply findAllPageable(Pageable pageable);

    UserRolePromoteReply promote(String username);

    UserRoleDemoteReply demote(String username);

    UserHasNextRoleReply hasNextRole(String username);

    UserHasPreviousRoleReply hasPreviousRole(String username);
}

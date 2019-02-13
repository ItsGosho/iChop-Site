package ichop.factories.user;

import ichop.domain.entities.users.User;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import ichop.service.role.UserRoles;

public interface UserFactory {

    User create(String username, String email, String password, UserRoles... userRoles);

    UserResetPasswordBindingModel create(String password, String confirmPassword);

    UserForgottenPasswordBindingModel create(String usernameOrEmail);

    UserRegisterBindingModel create(String username, String password, String confirmPassword, String email);
}

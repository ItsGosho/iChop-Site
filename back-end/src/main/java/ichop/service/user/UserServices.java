package ichop.service.user;

import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserRole;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {

    boolean isEmail(String value);

    User register(UserRegisterBindingModel userRegisterBindingModel);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    UserRole getRole(User user);
    User getUserByUsername(String username);
    User getUserByEmail(String email);


    void sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel);
    void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken);
}

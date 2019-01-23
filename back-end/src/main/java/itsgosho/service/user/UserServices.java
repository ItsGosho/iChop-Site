package itsgosho.service.user;

import itsgosho.domain.entities.users.User;
import itsgosho.domain.entities.users.UserRole;
import itsgosho.domain.models.binding.user.UserRegisterBindingModel;
import itsgosho.domain.models.binding.user.UserForgottenPasswordBindingModel;
import itsgosho.domain.models.binding.user.UserResetPasswordBindingModel;
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

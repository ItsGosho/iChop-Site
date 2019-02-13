package ichop.service.user;

import ichop.domain.entities.users.User;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {

    User register(UserRegisterBindingModel userRegisterBindingModel);

    void sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel);
    void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken);
}

package ichop.service.user;

import ichop.domain.entities.users.User;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import ichop.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {

    UserServiceModel register(UserRegisterBindingModel userRegisterBindingModel);

    void sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel);
    void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken);
}
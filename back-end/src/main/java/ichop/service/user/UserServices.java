package ichop.service.user;

import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import ichop.domain.models.service.user.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {

    UserServiceModel register(UserRegisterBindingModel userRegisterBindingModel);

    void sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel);
    void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken);

    void follow(UserServiceModel user,UserServiceModel userToFollow);
    void unfollow(UserServiceModel user,UserServiceModel userToUnfollow);

    void promote(UserServiceModel user);

    void demote(UserServiceModel user);
}

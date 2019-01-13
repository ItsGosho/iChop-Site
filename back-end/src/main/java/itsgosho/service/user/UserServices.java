package itsgosho.service.user;

import itsgosho.domain.entities.users.User;
import itsgosho.domain.entities.users.UserRole;
import itsgosho.domain.models.binding.UserRegisterBindingModel;
import itsgosho.domain.models.binding.UserResetPasswordBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {

    boolean register(UserRegisterBindingModel userRegisterBindingModel);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    UserRole getRole(User user);


    boolean sendPasswordResetEmail(UserResetPasswordBindingModel userResetPasswordBindingModel);
}

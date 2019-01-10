package itsgosho.service.user;

import itsgosho.domain.models.binding.UserRegisterBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {

    boolean register(UserRegisterBindingModel userRegisterBindingModel);
}

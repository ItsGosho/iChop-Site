package ichop.core.areas.user.services;

import ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import ichop.core.areas.user.domain.models.binding.UserRegisterBindingModel;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.common.service.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Set;

public interface UserServices extends UserDetailsService, BaseService<UserServiceModel> {


    UserServiceModel register(UserRegisterBindingModel userRegisterBindingModel);

    Set<UserRoleServiceModel> getInitialAuthorities();

    UserServiceModel findUserByUsername(String username);

    UserServiceModel findUserByEmail(String email);

    boolean isEmail(String value);

    boolean isUserExistsByUsername(String username);

    boolean isUserExistsByEmail(String email);

    long findTotalUsers();

    UserServiceModel findUserById(String id);

    void updateLastOnline(UserServiceModel user, LocalDateTime lastOnline);

    void updateUserLocation(UserServiceModel user, String userLocation);
}

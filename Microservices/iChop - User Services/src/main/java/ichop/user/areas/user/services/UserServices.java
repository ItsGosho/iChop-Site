package ichop.user.areas.user.services;

import ichop.user.areas.role.domain.models.service.UserRoleServiceModel;
import ichop.user.areas.user.domain.models.binding.UserRegisterBinding;
import ichop.user.areas.user.domain.models.service.UserServiceModel;
import ichop.user.common.service.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Set;

public interface UserServices extends UserDetailsService, BaseService<UserServiceModel> {


    UserServiceModel register(UserRegisterBinding userRegisterBinding);

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

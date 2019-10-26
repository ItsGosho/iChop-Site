package ichop.users.services;

import ichop.users.common.service.BaseService;
import ichop.users.domain.models.jms.register.UserRegisterRequest;
import ichop.users.domain.models.service.UserRoleServiceModel;
import ichop.users.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Set;

public interface UserServices extends UserDetailsService, BaseService<UserServiceModel> {


    UserServiceModel register(UserRegisterRequest userRegisterRequest);

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

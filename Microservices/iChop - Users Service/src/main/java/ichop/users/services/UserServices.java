package ichop.users.services;

import ichop.users.common.service.BaseService;
import ichop.users.domain.models.jms.register.UserRegisterRequest;
import ichop.users.domain.models.service.RoleServiceModel;
import ichop.users.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Set;

public interface UserServices extends UserDetailsService, BaseService<UserServiceModel> {


    UserServiceModel register(UserRegisterRequest userRegisterRequest);

    Set<RoleServiceModel> getInitialAuthorities();

    UserServiceModel findByUsername(String username);

    UserServiceModel findByEmail(String email);

    boolean isEmail(String value);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    long findTotalUsers();

    void updateLastOnline(UserServiceModel user, LocalDateTime lastOnline);

    void changePassword(String username, String password);

    void updateLocation(UserServiceModel user, String userLocation);
}

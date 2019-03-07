package ichop.service.user.crud;

import ichop.domain.models.service.user.UserServiceModel;

import java.time.LocalDateTime;

public interface UserCrudServices {

    boolean isEmail(String value);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    long getTotalUsers();

    UserServiceModel getUserById(String id);
    UserServiceModel getUserByUsername(String username);
    UserServiceModel getUserByEmail(String email);

    void save(UserServiceModel userServiceModel);

    void updateLastOnline(UserServiceModel userServiceModel,LocalDateTime dateTime);
}

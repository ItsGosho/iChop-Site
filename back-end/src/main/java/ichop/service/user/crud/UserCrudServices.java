package ichop.service.user.crud;

import ichop.domain.models.service.user.UserServiceModel;

public interface UserCrudServices {

    boolean isEmail(String value);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    long getTotalUsers();

    UserServiceModel getUserByUsername(String username);
    UserServiceModel getUserByEmail(String email);

    void save(UserServiceModel userServiceModel);
}

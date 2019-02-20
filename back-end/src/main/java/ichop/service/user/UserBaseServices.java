package ichop.service.user;

import ichop.domain.entities.users.User;
import ichop.domain.models.service.UserServiceModel;

public interface UserBaseServices {

    boolean isEmail(String value);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    long getTotalUsers();

    UserServiceModel getUserByUsername(String username);
    UserServiceModel getUserByEmail(String email);

    void save(UserServiceModel userServiceModelq);
}

package ichop.service.user;

import ichop.domain.entities.users.User;

public interface UserBaseServices {

    boolean isEmail(String value);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    long getTotalUsers();

    User getUserByUsername(String username);
    User getUserByEmail(String email);

    void save(User user);
}

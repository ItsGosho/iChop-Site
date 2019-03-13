package ichop.service.user.crud;

import ichop.domain.entities.users.User;
import ichop.domain.models.service.user.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserCrudServices {

    boolean isEmail(String value);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    long getTotalUsers();

    UserServiceModel getUserById(String id);
    UserServiceModel getUserByUsername(String username);
    UserServiceModel getUserByEmail(String email);

    void save(UserServiceModel user);

    void updateLastOnline(UserServiceModel user,LocalDateTime dateTime);

    void updateUserLocation(UserServiceModel user,String userLocation);

    boolean isUserAlreadyFollowedUser(UserServiceModel user,UserServiceModel followingUser);

    int getUserTotalFollowings(UserServiceModel user);
    int getUserTotalFollowers(UserServiceModel user);

    List<UserServiceModel> getFollowers(UserServiceModel user);

    List<UserServiceModel> findAll();

    Page<UserServiceModel> findAll(Pageable pageable);

    Page<UserServiceModel> findUsersByUsernameContains(String containingWord,Pageable pageable);

    Page<UserServiceModel> findUsersWhomHasRole(String role, Pageable pageable);
}

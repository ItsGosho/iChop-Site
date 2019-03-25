package com.ichop.core.service.user;

import com.ichop.core.domain.models.binding.user.UserForgottenPasswordBindingModel;
import com.ichop.core.domain.models.binding.user.UserRegisterBindingModel;
import com.ichop.core.domain.models.binding.user.UserResetPasswordBindingModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.List;

public interface UserServices extends UserDetailsService {

    UserServiceModel register(UserRegisterBindingModel userRegisterBindingModel);

    void sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel);
    void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken);

    void follow(UserServiceModel user,UserServiceModel userToFollow);
    void unfollow(UserServiceModel user,UserServiceModel userToUnfollow);

    void sendUpdateAvatarRequest(String username,String imageAsBase64String);


    UserServiceModel promote(UserServiceModel user);

    UserServiceModel demote(UserServiceModel user);

    UserServiceModel findUserByUsername(String username);

    UserServiceModel findUserByEmail(String email);

    boolean isEmail(String value);
    boolean isUserExistsByUsername(String username);
    boolean isUserExistsByEmail(String email);

    long findTotalUsers();

    UserServiceModel findUserById(String id);

    void updateLastOnline(UserServiceModel user, LocalDateTime dateTime);

    void updateUserLocation(UserServiceModel user,String userLocation);

    boolean isUserAlreadyFollowedUser(UserServiceModel user,UserServiceModel followingUser);

    int findUserTotalFollowings(UserServiceModel user);
    int findUserTotalFollowers(UserServiceModel user);

    List<UserServiceModel> getFollowers(UserServiceModel user);

    Page<UserServiceModel> findUsersByUsernameContains(String containingWord, Pageable pageable);

    Page<UserServiceModel> findUsersWhomHasRole(String role, Pageable pageable);

    Page<UserServiceModel> findAll(Pageable pageable);
}

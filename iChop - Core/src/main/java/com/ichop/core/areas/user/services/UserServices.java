package com.ichop.core.areas.user.services;

import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.areas.user.domain.models.binding.UserForgottenPasswordBindingModel;
import com.ichop.core.areas.user.domain.models.binding.UserRegisterBindingModel;
import com.ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Set;

public interface UserServices extends UserDetailsService {

    UserServiceModel register(UserRegisterBindingModel userRegisterBindingModel);

    Set<UserRoleServiceModel> getInitialAuthorities();

    void sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel);
    void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken);

    void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, UserServiceModel user);


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

    Page<UserServiceModel> findUsersByUsernameContains(String containingWord, Pageable pageable);

    Page<UserServiceModel> findUsersWhomHasRole(String role, Pageable pageable);

    Page<UserServiceModel> findAll(Pageable pageable);
}

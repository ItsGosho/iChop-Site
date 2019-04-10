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
import java.util.List;
import java.util.Set;

public interface UserServices extends UserDetailsService {

    /*
     *
     * Registers a user.
     *
     * @throws UserAlreadyExistsException if user with that username/email already exists.
     * @throws UserPasswordNotValidException if the provided passwords doesn't match.
     *
     * */
    UserServiceModel register(UserRegisterBindingModel userRegisterBindingModel);

    /*
     *
     * Get the initial authorities by users count in the database.
     * @returns Set<UserRoleServiceModel> which contains all roles
     * if in the database there are no users , and only a User role
     * if there are.
     *
     * */
    Set<UserRoleServiceModel> getInitialAuthorities();

    /*
     *
     * Sends password reset email via the email services.
     *
     * */
    void sendPasswordResetEmail(UserForgottenPasswordBindingModel userForgottenPasswordBindingModel);


    /*
     *
     * Resets the user password by the provided token.
     * @throws TokenNotValidException if the token is not valid
     * @throws UserPasswordNotValidException if the passwords doesn't match
     *
     * */
    void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, String resetToken);

    /*
     *
     * Resets the user password by provided user.
     * @throws UserPasswordNotValidException if the passwords doesn't match
     *
     * */
    void resetPassword(UserResetPasswordBindingModel userResetPasswordBindingModel, UserServiceModel user);

    /*
     *
     * Promotes user to his next higher role.
     * @throws UserNotFoundException if the user is not found.
     * @throws RoleNotFoundException if there is no next role or if the next role is OWNER
     *
     * */
    UserServiceModel promote(UserServiceModel user);

    /*
     *
     * Demotes user to his next lower role.
     * @throws UserNotFoundException if the user is not found.
     * @throws RoleNotFoundException if there is no previous role.
     *
     * */
    UserServiceModel demote(UserServiceModel user);

    /*
     *
     * Finds user by his username.
     * @return null in case of not existing user
     *
     * */
    UserServiceModel findUserByUsername(String username);

    /*
     *
     * Finds user by his email..
     * @return null in case of not existing user
     *
     * */
    UserServiceModel findUserByEmail(String email);

    /*
     *
     * @return if the provided value match
     * the pattern of email
     *
     * */
    boolean isEmail(String value);

    /*
     *
     * @return if user exists with the provided @param username
     *
     * */
    boolean isUserExistsByUsername(String username);

    /*
     *
     * @return if user exists with the provided @param email
     *
     * */
    boolean isUserExistsByEmail(String email);

    /*
     *
     * @return the total users in the database
     *
     * */
    long findTotalUsers();

    /*
     *
     * Finds user by his id
     * @return null in case of not existing user
     *
     * */
    UserServiceModel findUserById(String id);

    /*
     *
     * Updates the last online column of user.
     *
     * */
    void updateLastOnline(UserServiceModel user, LocalDateTime lastOnline);

    /*
     *
     * Updates the user location.
     *
     * */
    void updateUserLocation(UserServiceModel user, String userLocation);

    /*
    *
    * Finds all users where their username contains @param containingWord
    *
    * */
    Page<UserServiceModel> findUsersByUsernameContains(String containingWord, Pageable pageable);

    /*
     *
     * Finds all users where they have the provided role.
     * The pageable will determinate sort,size and entries to get.
     *
     * */
    Page<UserServiceModel> findUsersWhomHasRole(String role, Pageable pageable);

    /*
     *
     * Finds all users where they have the provided role.
     *
     * */
    List<UserServiceModel> findUsersWhomHasRole(String role);

    /*
     *
     * Finds all users.
     *
     * */
    Page<UserServiceModel> findAll(Pageable pageable);
}

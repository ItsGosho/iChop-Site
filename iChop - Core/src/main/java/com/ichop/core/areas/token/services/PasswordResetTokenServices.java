package com.ichop.core.areas.token.services;

import com.ichop.core.areas.token.domain.models.binding.PasswordResetTokenCreateBindingModel;
import com.ichop.core.areas.token.domain.models.service.PasswordResetTokenServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

import java.util.List;

public interface PasswordResetTokenServices {

    /*
    *
    * Determines if the provided token is valid by
    * his existence and date of expiration.
    * @returns false if the token is not found or expired
    *
    * */
    boolean isValid(String token);

    /*
    *
    * Determines if the token validity date
    * is expired ,compared to current date.
    *
    * */
    boolean isTokenDateExpired(PasswordResetTokenServiceModel passwordResetToken);

    /*
    *
    * Creates password reset token.In case of existing
    * the old one will be removed.
    *
    * */
    PasswordResetTokenServiceModel create(PasswordResetTokenCreateBindingModel bindingModel);

    /*
    *
    * Deletes the oldest token of user.
    * In case of not existing ,nothing
    * will happen.
    *
    * */
    void deleteOldestByUser(UserServiceModel user);

    /*
    *
    * Finds token by token.
    * @returns PasswordResetTokenServiceModel which can be null in case of not found token
    *
    * */
    PasswordResetTokenServiceModel findByToken(String token);

    /*
    *
    * Finds all token which expirity date is before current date
    *
    * */
    List<PasswordResetTokenServiceModel> findAllExpired();

    /*
    *
    * Deletes token by id
    * @throws TokenNotFoundException if token doesn't exists
    *
    * */
    void deleteTokenById(String id);
}

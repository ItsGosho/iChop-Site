package com.ichop.core.areas.comment.services;

import com.ichop.core.areas.comment.domain.models.binding.CommentCreateBindingModel;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;


/*
*
* Responsible for the creation of comment entity
*
* */
public interface CommentServices {

    /*
    * Creates comment entity.
    * @param bindingModel  the binding model that will be convert to Comment entity
    * @throws UserNotFoundException if the creator is null
    * @throws ThreadNotFoundException if the thread is null
    * @returns CommentServiceModel which is always valid
    *
    * */
    CommentServiceModel create(CommentCreateBindingModel bindingModel);


    /*
    *
    * Get the total comments of given user
    * @param user  the user that the comment belong to
    * @throws UserNotFoundException if the user is null
    * @returns the total comment of the user
    *
    * */
    int getTotalOfUser(UserServiceModel user);


    /*
    *
    * Get comment by his id
    * @param commentId the id of the comment
    * @returns null if the comment is not found
    *
    * */
    CommentServiceModel findById(String commentId);


    /*
    *
    * Delete the comment by his id
    * @param commenId the id of the comment
    * @throws CommentNotFoundException if the comment is not found
    *
    * */
    void delete(String commentId);


    /*
    *
    * Delete the comment by his representing service model
    * @param comment need to have at least id to be found
    * @throws CommentNotFoundException if the comment is null or cannot be found
    *
    * */
    void deleteByModel(CommentServiceModel comment);
}

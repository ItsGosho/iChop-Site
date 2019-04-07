package com.ichop.core.areas.reaction.web.controllers;

import com.google.gson.Gson;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.reaction.services.CommentReactionServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReactionApiController extends BaseController {


    private final CommentServices commentServices;
    private final UserServices userServices;
    private final CommentReactionServices commentReactionServices;

    @Autowired
    public ReactionApiController(CommentServices commentServices, UserServices userServices, CommentReactionServices commentReactionServices) {
        this.commentServices = commentServices;
        this.userServices = userServices;
        this.commentReactionServices = commentReactionServices;
    }


    @GetMapping(value = URLConstants.IS_COMMENT_ALREADY_REACTED_BY_USER,produces = "application/json")
    @ResponseBody
    public String isCommentAlreadyReactedByUser(@PathVariable String commentId, @RequestParam String userUsername) {

        CommentServiceModel comment = this.commentServices.findById(commentId);
        UserServiceModel user = this.userServices.findUserByUsername(userUsername);

        if(comment == null || user == null){
            return new Gson().toJson(false);
        }

        if(comment.getCreator().getUsername().equals(user.getUsername())){
            return new Gson().toJson(true);
        }

        boolean isReactionPresent = this.commentReactionServices.isLikedByUser(user,comment);
        return new Gson().toJson(isReactionPresent);
    }
}

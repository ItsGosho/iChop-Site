package com.ichop.core.areas.comment.web.controllers;

import com.google.gson.Gson;
import com.ichop.core.areas.comment.domain.models.binding.CommentCreateBindingModel;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.services.ThreadServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class CommentController extends BaseController {

    private final CommentServices commentServices;
    private final ModelMapper modelMapper;
    private final ThreadServices threadServices;

    @Autowired
    public CommentController(CommentServices commentServices, ModelMapper modelMapper, ThreadServices threadServices) {
        this.commentServices = commentServices;
        this.modelMapper = modelMapper;
        this.threadServices = threadServices;
    }


    @PreAuthorize("hasAuthority('MODERATOR') or @commentServicesImp.findById(#commentId).creator.username.equals(principal.username)")
    @PostMapping(URLConstants.COMMENT_DELETE_POST)
    public String proceedCommentDeletion(@PathVariable String commentId) {

        CommentServiceModel commentServiceModel = this.commentServices.findById(commentId);
        this.commentServices.delete(commentId);

        String threadId = commentServiceModel.getThread().getId();
        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}", threadId);
        return super.redirect(redirectUrl);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = URLConstants.THREAD_CREATE_COMMENT_POST, produces = "application/json")
    @ResponseBody
    public String proceedCommentCreation(@PathVariable String threadId, CommentCreateBindingModel commentCreateBindingModel, Principal principal) {

        ThreadServiceModel threadServiceModel = this.threadServices.findById(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);
        commentCreateBindingModel.setCreator(userServiceModel);
        commentCreateBindingModel.setThread(threadServiceModel);
        this.commentServices.create(commentCreateBindingModel);

        return new Gson().toJson(true);
    }

}

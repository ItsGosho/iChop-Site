package com.ichop.core.areas.post.web.controllers;

import com.ichop.core.areas.post.domain.models.binding.PostCreateBindingModel;
import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.post.services.PostServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PostController extends BaseController {

    private final UserServices userServices;
    private final PostServices postServices;
    private final ModelMapper modelMapper;

    @Autowired
    public PostController(UserServices userServices, PostServices postServices, ModelMapper modelMapper) {
        this.userServices = userServices;
        this.postServices = postServices;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.CREATE_POST_POST)
    public String proceedPostCreation(@PathVariable String userUsername, Principal principal, @Valid PostCreateBindingModel postCreateBindingModel, BindingResult bindingResult) {

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",userUsername);

        if(bindingResult.hasErrors()){
            return super.redirect(redirectUrl);
        }

        UserServiceModel user = this.userServices.findUserByUsername(userUsername);
        UserServiceModel creatorServiceModel = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);
        this.postServices.create(user,creatorServiceModel,postCreateBindingModel);

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("hasAuthority('MODERATOR') or @postServicesImp.findById(#postId).creator.username.equals(principal.username) or @postServicesImp.findById(#postId).user.username.equals(principal.username)")
    @PostMapping(URLConstants.DELETE_POST_POST)
    public String proceedPostDeletion(@PathVariable String postId) {

        PostServiceModel postServiceModel = this.postServices.findById(postId);
        this.postServices.delete(postServiceModel);
        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",postServiceModel.getUser().getUsername());

        return super.redirect(redirectUrl);
    }

}

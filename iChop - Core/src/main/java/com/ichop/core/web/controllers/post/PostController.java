package com.ichop.core.web.controllers.post;

import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.models.binding.post.PostCreateBindingModel;
import com.ichop.core.domain.models.service.post.PostServiceModel;
import com.ichop.core.domain.entities.users.User;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.service.post.PostServices;
import com.ichop.core.service.user.UserServices;
import com.ichop.core.web.controllers.BaseController;
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
    public String createPost(@PathVariable String userUsername, Principal principal, @Valid PostCreateBindingModel postCreateBindingModel, BindingResult bindingResult) {

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",userUsername);

        if(bindingResult.hasErrors()){
            return super.redirect(redirectUrl);
        }

        UserServiceModel user = this.userServices.findUserByUsername(userUsername);
        UserServiceModel creatorServiceModel = this.modelMapper.map((User)((Authentication) principal).getPrincipal(), UserServiceModel.class);

        PostServiceModel postServiceModel = this.postServices.createPost(user,creatorServiceModel,postCreateBindingModel);

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("hasAuthority('MODERATOR') or @postCrudServicesImp.getById(#postId).creator.username.equals(principal.username) or @postCrudServicesImp.getById(#postId).user.username.equals(principal.username)")
    @PostMapping(URLConstants.DELETE_POST_POST)
    public String createPost(@PathVariable String postId) {

        PostServiceModel postServiceModel = this.postServices.findPostById(postId);
        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",postServiceModel.getUser().getUsername());
        this.postServices.deletePost(postServiceModel);

        return super.redirect(redirectUrl);
    }

}

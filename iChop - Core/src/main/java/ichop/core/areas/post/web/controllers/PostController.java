package ichop.core.areas.post.web.controllers;

import ichop.core.areas.post.domain.models.binding.PostCreateBindingModel;
import ichop.core.areas.post.domain.models.service.PostServiceModel;
import ichop.core.areas.post.services.PostServices;
import ichop.core.base.BaseController;
import ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PostController extends BaseController {

    private final PostServices postServices;

    @Autowired
    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.CREATE_POST_POST)
    public String proceedPostCreation(@PathVariable String userUsername, @Valid @ModelAttribute PostCreateBindingModel postCreateBindingModel, BindingResult bindingResult) {

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",userUsername);

        if(bindingResult.hasErrors()){
            return super.redirect(redirectUrl);
        }

        this.postServices.create(postCreateBindingModel);

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("hasAuthority('MODERATOR') or @postServicesImp.findById(#postId).creator.username.equals(principal.username) or @postServicesImp.findById(#postId).user.username.equals(principal.username)")
    @PostMapping(URLConstants.DELETE_POST_POST)
    public String proceedPostDeletion(@PathVariable String postId) {

        PostServiceModel postServiceModel = this.postServices.findById(postId);
        this.postServices.deleteByModel(postServiceModel);
        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",postServiceModel.getUser().getUsername());

        return super.redirect(redirectUrl);
    }

}

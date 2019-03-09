package ichop.web.controllers.user;

import ichop.constants.URLConstants;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.user.PostCreateBindingModel;
import ichop.domain.models.service.user.PostServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.user.PostServices;
import ichop.service.user.crud.UserCrudServices;
import ichop.web.controllers.BaseController;
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
public class UserPostsController extends BaseController {

    private final UserCrudServices userCrudServices;
    private final PostServices postServices;
    private final ModelMapper modelMapper;

    @Autowired
    public UserPostsController(UserCrudServices userCrudServices, PostServices postServices, ModelMapper modelMapper) {
        this.userCrudServices = userCrudServices;
        this.postServices = postServices;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_CREATE_POST_POST)
    public String createPost(@PathVariable String userUsername, Principal principal, @Valid PostCreateBindingModel postCreateBindingModel, BindingResult bindingResult) {

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",userUsername);

        if(bindingResult.hasErrors()){
            return super.redirect(redirectUrl);
        }

        UserServiceModel user = this.userCrudServices.getUserByUsername(userUsername);
        UserServiceModel creatorServiceModel = this.modelMapper.map((User)((Authentication) principal).getPrincipal(), UserServiceModel.class);

        PostServiceModel postServiceModel = this.postServices.create(user,creatorServiceModel,postCreateBindingModel);

        return super.redirect(redirectUrl);
    }

}

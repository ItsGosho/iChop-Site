package ichop.core.areas.user.web.controllers;

import ichop.core.areas.user.domain.models.view.user_all.UsersAllViewModel;
import ichop.core.areas.user.domain.models.view.user_profile.UserProfileViewModel;
import ichop.core.areas.user.helpers.view.user_all.UsersAllViewHelper;
import ichop.core.areas.user.helpers.view.user_profile.UserProfileViewHelper;
import ichop.core.base.BaseController;
import ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends BaseController {

    private final UsersAllViewHelper usersAllViewHelper;
    private final UserProfileViewHelper userProfileViewHelper;

    @Autowired
    public UserController(UsersAllViewHelper usersAllViewHelper, UserProfileViewHelper userProfileViewHelper) {
        this.usersAllViewHelper = usersAllViewHelper;
        this.userProfileViewHelper = userProfileViewHelper;
    }


    @GetMapping(URLConstants.USER_PROFILE_GET)
    public ModelAndView getUserProfile(@PathVariable String username, ModelAndView modelAndView) {

        UserProfileViewModel user = this.userProfileViewHelper.create(username);

        modelAndView.addObject("user", user);

        return super.page("user/profile/user-profile", String.format("%s`s profile", user.getUsername()), modelAndView);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(URLConstants.USER_ALL_GET)
    public ModelAndView getAllUsers(ModelAndView modelAndView,
                                 @PageableDefault(size = 10, sort = "registrationDate", direction = Sort.Direction.DESC) Pageable pageable,
                                 @RequestParam(value = "isUsernameLike",required = false) String isUsernameLike,
                                 @RequestParam(value = "hasRole",required = false) String hasRole) {


        Page<UsersAllViewModel> users;
        if(isUsernameLike != null) {
            users = this.usersAllViewHelper.createWhereUsernameContains(isUsernameLike, pageable);
        }else if(hasRole != null){
            users = this.usersAllViewHelper.createWhereRoleIsPresent(hasRole, pageable);
        }else{
            users = this.usersAllViewHelper.create(pageable);
        }

        modelAndView.addObject("users",users);

        return super.page("user/users-all", "All Users", modelAndView);
    }


}

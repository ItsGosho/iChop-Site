package ichop.web.controllers.user;

import ichop.constants.URLConstants;
import ichop.web.controllers.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserPostsController extends BaseController {


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_CREATE_POST_POST)
    public ModelAndView createPost(@PathVariable String userId, Principal principal) {



        return null;
    }

}

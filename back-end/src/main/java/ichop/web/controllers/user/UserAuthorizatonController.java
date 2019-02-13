package ichop.web.controllers.user;

import ichop.constants.URLConstants;
import ichop.exceptions.user.UserException;
import ichop.exceptions.user.UserExceptionMessages;
import ichop.web.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAuthorizatonController extends BaseController {

    @GetMapping(URLConstants.UNAUTHORIZED)
    public ModelAndView noPermission(){
        throw new UserException(UserExceptionMessages.NOT_AUTHORIZED);
    }
}

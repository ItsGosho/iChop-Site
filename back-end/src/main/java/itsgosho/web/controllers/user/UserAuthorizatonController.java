package itsgosho.web.controllers.user;

import itsgosho.web.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAuthorizatonController extends BaseController {

    @GetMapping("/unauthorized")
    public ModelAndView noPermission(){
        return super.viewWithMessage("base-page","notification/error","Whoops you dont have permission to access this page!");
    }
}

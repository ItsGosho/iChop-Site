package itsgosho.web.controllers;

import itsgosho.components.email.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController {

    private final EmailServices emailServices;

    @Autowired
    public HomeController(EmailServices emailServices) {
        this.emailServices = emailServices;
    }

    @GetMapping("")
    public ModelAndView home(ModelAndView modelAndView) {
        return super.page("base-page","shit","iChop",modelAndView);
    }

   // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/test")
    public ModelAndView testHome(ModelAndView modelAndView) {
        return super.page("base-page","auth/reset_password-form","Reset Password");
    }
}

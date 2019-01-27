package itsgosho.web.controllers;

import itsgosho.components.email.EmailServices;
import itsgosho.domain.models.view.thread.ThreadHomepageViewModel;
import itsgosho.service.thread.ThreadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController {

    private final EmailServices emailServices;
    private final ThreadServices threadServices;

    @Autowired
    public HomeController(EmailServices emailServices, ThreadServices threadServices) {
        this.emailServices = emailServices;
        this.threadServices = threadServices;
    }

    @GetMapping("")
    public ModelAndView home(ModelAndView modelAndView,@PageableDefault(size = 3) Pageable pageable) {
        Page<ThreadHomepageViewModel> pages = this.threadServices.listAllByPage(pageable);
        modelAndView.addObject("threads",pages);
        modelAndView.addObject("totalPages",pages.getTotalPages());
        return super.page("base-page","home-page","iChop",modelAndView);
    }



   // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/test")
    public ModelAndView testHome(ModelAndView modelAndView) {
        return super.page("base-page","auth/reset_password-form","Reset Password");
    }
}

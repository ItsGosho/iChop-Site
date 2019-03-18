package ichop.web.controllers;

import ichop.constants.URLConstants;
import ichop.domain.models.view.home.ThreadHomepageViewModel;
import ichop.service.thread.view.ThreadViewServices;
import ichop.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class HomeController extends BaseController {


    private final ThreadViewServices threadViewServices;
    private final UserServices userServices;

    @Autowired
    public HomeController(ThreadViewServices threadViewServices, UserServices userServices) {
        this.threadViewServices = threadViewServices;
        this.userServices = userServices;
    }


    @GetMapping(value = URLConstants.HOME_GET)
    public ModelAndView home(ModelAndView modelAndView, @PageableDefault(size = 3, sort = "createdOn", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ThreadHomepageViewModel> pages = this.threadViewServices.listAllByPage(pageable);

        modelAndView.addObject("threads", pages);
        modelAndView.addObject("totalPages", pages.getTotalPages());

        return super.page("home-page", "iChop", modelAndView);
    }


    // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = URLConstants.TEST)
    @ResponseBody
    public String testHome(ModelAndView modelAndView, HttpServletRequest httpServletRequest) throws IOException {

        //this.userServices.follow(this.userServices.getUserProfileViewModel("123"),this.userServices.getUserProfileViewModel("1234"));
        this.userServices.unfollow(this.userServices.findUserByUsername("123"),this.userServices.findUserByUsername("1234"));

        return "<h1>"+ "brrr" + "</h1>";
    }
}

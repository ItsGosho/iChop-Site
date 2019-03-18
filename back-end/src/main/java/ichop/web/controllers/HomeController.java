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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        return super.page("home-page", "Home", modelAndView);
    }


    // @PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("@userServicesImp.findUserByUsername(#user1Username) != null")
    @GetMapping(value = URLConstants.TEST)
    @ResponseBody
    public String testHome(ModelAndView modelAndView, HttpServletRequest httpServletRequest, @RequestParam(required = true) String user1Username) throws IOException {


        return "<h1>"+ "brrr" + "</h1>";
    }
}

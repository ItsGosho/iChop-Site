package ichop.web.controllers;

import ichop.constants.URLConstants;
import ichop.domain.models.view.thread.ThreadHomepageViewModel;
import ichop.service.threads.thread.view.ThreadViewServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {


    private final ThreadViewServices threadViewServices;

    public HomeController(ThreadViewServices threadViewServices) {
        this.threadViewServices = threadViewServices;
    }


    @GetMapping(value = URLConstants.HOME_GET)
    public ModelAndView home(ModelAndView modelAndView, @PageableDefault(size = 3, sort = "createdOn", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ThreadHomepageViewModel> pages = this.threadViewServices.listAllByPage(pageable);

        modelAndView.addObject("threads", pages);
        modelAndView.addObject("totalPages", pages.getTotalPages());
        return super.page("base-page", "home-page", "iChop", modelAndView);
    }


    // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = URLConstants.TEST)
    public void testHome(ModelAndView modelAndView) {

    }
}

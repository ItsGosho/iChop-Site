package ichop.web.controllers;

import ichop.constants.URLConstants;
import ichop.domain.entities.users.User;
import ichop.domain.models.view.thread.ThreadHomepageViewModel;
import ichop.repository.user.UserRepository;
import ichop.service.threads.thread.view.ThreadViewServices;
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
    private final UserRepository userRepository;

    public HomeController(ThreadViewServices threadViewServices, UserRepository userRepository) {
        this.threadViewServices = threadViewServices;
        this.userRepository = userRepository;
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
    @ResponseBody
    public String testHome(ModelAndView modelAndView, HttpServletRequest httpServletRequest) throws IOException {

        User user = this.userRepository.findUserByUsername("1234");
        User user2 = this.userRepository.findUserByUsername("123");

        boolean result = this.userRepository.isUserAlreadyFollowedUser(user.getId(),user2.getId());
        return "<h1>" + result + "</h1>";
    }
}

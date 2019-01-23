package itsgosho.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/thread")
public class ThreadController extends BaseController {


    //@PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("/create")
    public ModelAndView testHome(ModelAndView modelAndView) {
        return super.page("base-page","thread/thread-create","Create thread");
    }
}

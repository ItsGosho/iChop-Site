package itsgosho.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController {

    @GetMapping("")
    public ModelAndView home(ModelAndView modelAndView) {
        return super.page("base-page","shit","iChop",modelAndView);
    }
}

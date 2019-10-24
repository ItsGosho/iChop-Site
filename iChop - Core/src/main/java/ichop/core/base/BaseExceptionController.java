package ichop.core.base;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseExceptionController extends BaseController {

    public ModelAndView errorPage(String message) {
        return super.viewWithMessage("notification/error","Error",message);
    }


}

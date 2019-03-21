package com.ichop.core.web.controllers.exception;

import com.ichop.core.web.controllers.BaseController;
import org.springframework.web.servlet.ModelAndView;

public abstract class ExceptionBaseController extends BaseController {

    public ModelAndView errorPage(String message) {
        return super.viewWithMessage("base-page","Error","notification/error",message);
    }


}

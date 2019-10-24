package ichop.core.areas.report.web.controllers;

import ichop.core.areas.report.exceptions.ReportAlreadyMadeException;
import ichop.core.areas.report.exceptions.ReportNotFoundException;
import ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static ichop.core.areas.report.constants.ReportExceptionMessages.REPORT_ALREADY_MADE;
import static ichop.core.areas.report.constants.ReportExceptionMessages.REPORT_NOT_FOUND;

@ControllerAdvice
public class ReportExceptionController extends BaseExceptionController {

    @ExceptionHandler(ReportNotFoundException.class)
    public ModelAndView reportNotFound(){
        return super.errorPage(REPORT_NOT_FOUND);
    }

    @ExceptionHandler(ReportAlreadyMadeException.class)
    public ModelAndView reportAlreadyMade(){
        return super.errorPage(REPORT_ALREADY_MADE);
    }

}

package ichop.web.controllers.thread;

import ichop.constants.URLConstants;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.threads.comment.crud.CommentCrudServices;
import ichop.service.threads.report.ReportServices;
import ichop.service.threads.thread.crud.ThreadCrudServices;
import ichop.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class ReportController extends BaseController {

    private final CommentCrudServices commentCrudServices;
    private final ThreadCrudServices threadCrudServices;
    private final ReportServices reportServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportController(CommentCrudServices commentCrudServices, ThreadCrudServices threadCrudServices, ReportServices reportServices, ModelMapper modelMapper) {
        this.commentCrudServices = commentCrudServices;
        this.threadCrudServices = threadCrudServices;
        this.reportServices = reportServices;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.COMMENT_REPORT_POST)
    public ModelAndView reportComment(@PathVariable String id, Principal principal, @RequestParam String reason){
        CommentServiceModel commentServiceModel = this.commentCrudServices.getById(id);
        UserServiceModel userServiceModel = this.modelMapper.map((User)((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.reportServices.addReport(commentServiceModel,userServiceModel,reason);

        return super.viewWithMessage("base-page","notification/info","This comment was succesful reported!");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.THREAD_REPORT_POST)
    public ModelAndView reportThread(@PathVariable String id, Principal principal, @RequestParam String reason){
        ThreadServiceModel threadServiceModel = this.threadCrudServices.getThread(id);
        UserServiceModel userServiceModel = this.modelMapper.map((User)((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.reportServices.addReport(threadServiceModel,userServiceModel,reason);

        return super.viewWithMessage("base-page","notification/info","This thread was succesful reported!");
    }
}

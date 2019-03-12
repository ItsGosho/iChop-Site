package ichop.web.controllers.report;

import ichop.constants.URLConstants;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.comment.crud.CommentCrudServices;
import ichop.service.post.crud.PostCrudServices;
import ichop.service.report.ReportServices;
import ichop.service.thread.crud.ThreadCrudServices;
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
    private final PostCrudServices postCrudServices;
    private final ReportServices reportServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportController(CommentCrudServices commentCrudServices, ThreadCrudServices threadCrudServices, PostCrudServices postCrudServices, ReportServices reportServices, ModelMapper modelMapper) {
        this.commentCrudServices = commentCrudServices;
        this.threadCrudServices = threadCrudServices;
        this.postCrudServices = postCrudServices;
        this.reportServices = reportServices;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.COMMENT_REPORT_POST)
    public String reportComment(@PathVariable String commentId, Principal principal, @RequestParam String reason) {
        CommentServiceModel commentServiceModel = this.commentCrudServices.getById(commentId);
        UserServiceModel userServiceModel = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.reportServices.addReport(commentServiceModel, userServiceModel, reason);

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}",commentServiceModel.getThread().getId());

        return redirectUrl;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.THREAD_REPORT_POST)
    public String reportThread(@PathVariable String threadId, Principal principal, @RequestParam String reason) {
        ThreadServiceModel threadServiceModel = this.threadCrudServices.getThread(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.reportServices.addReport(threadServiceModel, userServiceModel, reason);

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}",threadServiceModel.getId());

        return redirectUrl;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.POST_REPORT_POST)
    public String reportPost(@PathVariable String postId, Principal principal, @RequestParam String reason) {

        PostServiceModel postServiceModel = this.postCrudServices.getById(postId);
        UserServiceModel userServiceModel = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.reportServices.addReport(postServiceModel, userServiceModel, reason);

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",postServiceModel.getCreator().getUsername());

        return redirectUrl;
    }
}

package ichop.core.areas.comment.web.controllers;

import com.google.gson.Gson;
import ichop.core.areas.comment.models.CommentCreateBindingModel;
import ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import ichop.core.areas.comment.services.CommentServices;
import ichop.core.areas.thread.services.ThreadServices;
import ichop.core.base.BaseController;
import ichop.core.constants.URLConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class CommentController extends BaseController {

    private final CommentServices commentServices;
    private final ModelMapper modelMapper;
    private final ThreadServices threadServices;

    @Autowired
    public CommentController(CommentServices commentServices, ModelMapper modelMapper, ThreadServices threadServices) {
        this.commentServices = commentServices;
        this.modelMapper = modelMapper;
        this.threadServices = threadServices;
    }


    @PreAuthorize("hasAuthority('MODERATOR') or @commentServicesImp.findById(#commentId).creator.username.equals(principal.username)")
    @PostMapping(URLConstants.COMMENT_DELETE_POST)
    public String proceedCommentDeletion(@PathVariable String commentId) {

        CommentServiceModel commentServiceModel = this.commentServices.findById(commentId);
        this.commentServices.delete(commentId);

        String threadId = commentServiceModel.getThread().getId();
        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}", threadId);
        return super.redirect(redirectUrl);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = URLConstants.THREAD_CREATE_COMMENT_POST, produces = "application/json")
    @ResponseBody
    public String proceedCommentCreation(@PathVariable String threadId,
                                         @Valid @ModelAttribute CommentCreateBindingModel commentCreateBindingModel,
                                         BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return new Gson().toJson(false);
        }

        this.commentServices.create(commentCreateBindingModel);

        return new Gson().toJson(true);
    }

}

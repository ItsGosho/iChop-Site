package ichop.web.controllers.comment;

import ichop.constants.URLConstants;
import ichop.domain.models.service.CommentServiceModel;
import ichop.exceptions.thread.CommentNotFoundException;
import ichop.service.thread.crud.CommentCrudServices;
import ichop.web.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController extends BaseController {

    private final CommentCrudServices commentCrudServices;

    public CommentController(CommentCrudServices commentCrudServices) {
        this.commentCrudServices = commentCrudServices;
    }


    @GetMapping(URLConstants.COMMENT_DELETE)
    public ModelAndView deleteComment(@PathVariable String id) {

        CommentServiceModel commentServiceModel = this.commentCrudServices.getById(id);

        if (commentServiceModel != null) {
            String threadId = commentServiceModel.getThread().getId();

            this.commentCrudServices.delete(commentServiceModel);

            String redirectUrl = URLConstants.THREAD_READ_GET.replace("{id}",threadId);
            return super.redirect(redirectUrl);
        }

        throw new CommentNotFoundException();
    }

}

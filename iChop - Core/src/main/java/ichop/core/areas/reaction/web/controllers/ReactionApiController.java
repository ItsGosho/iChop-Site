package ichop.core.areas.reaction.web.controllers;

import com.google.gson.Gson;
import ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import ichop.core.areas.comment.services.CommentServices;
import ichop.core.areas.reaction.services.CommentReactionServices;
import ichop.core.areas.reaction.services.ThreadReactionServices;
import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.thread.services.ThreadServices;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.services.UserServices;
import ichop.core.base.BaseController;
import ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReactionApiController extends BaseController {


    private final CommentServices commentServices;
    private final ThreadServices threadServices;
    private final UserServices userServices;
    private final CommentReactionServices commentReactionServices;
    private final ThreadReactionServices threadReactionServices;

    @Autowired
    public ReactionApiController(CommentServices commentServices, ThreadServices threadServices, UserServices userServices, CommentReactionServices commentReactionServices, ThreadReactionServices threadReactionServices) {
        this.commentServices = commentServices;
        this.threadServices = threadServices;
        this.userServices = userServices;
        this.commentReactionServices = commentReactionServices;
        this.threadReactionServices = threadReactionServices;
    }


    @GetMapping(value = URLConstants.IS_COMMENT_ALREADY_REACTED_BY_USER, produces = "application/json")
    @ResponseBody
    public String isCommentAlreadyReactedByUser(@PathVariable String commentId, @RequestParam String userUsername) {

        CommentServiceModel comment = this.commentServices.findById(commentId);
        UserServiceModel user = this.userServices.findUserByUsername(userUsername);

        if (comment == null || user == null) {
            return new Gson().toJson(false);
        }

        if (comment.getCreator().getUsername().equals(user.getUsername())) {
            return new Gson().toJson(true);
        }

        boolean isReactionPresent = this.commentReactionServices.isReactedByUser(user, comment);
        return new Gson().toJson(isReactionPresent);
    }


    @GetMapping(value = URLConstants.IS_THREAD_ALREADY_REACTED_BY_USER, produces = "application/json")
    @ResponseBody
    public String isThreadAlreadyReactedByUser(@PathVariable String threadId, @RequestParam String userUsername) {

        ThreadServiceModel thread = this.threadServices.findById(threadId);
        UserServiceModel user = this.userServices.findUserByUsername(userUsername);

        if (thread == null || user == null) {
            return new Gson().toJson(false);
        }

        if (thread.getCreator().getUsername().equals(user.getUsername())) {
            return new Gson().toJson(true);
        }

        boolean isReactionPresent = this.threadReactionServices.isReactedByUser(user, thread);
        return new Gson().toJson(isReactionPresent);
    }

}

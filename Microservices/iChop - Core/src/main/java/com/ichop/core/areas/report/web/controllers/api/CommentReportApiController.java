package com.ichop.core.areas.report.web.controllers.api;

import com.google.gson.Gson;
import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.report.services.CommentReportServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentReportApiController extends BaseController {

    private final CommentServices commentServices;
    private final CommentReportServices commentReportServices;
    private final UserServices userServices;

    @Autowired
    public CommentReportApiController(CommentServices commentServices, CommentReportServices commentReportServices, UserServices userServices) {
        this.commentServices = commentServices;
        this.commentReportServices = commentReportServices;
        this.userServices = userServices;
    }


    @GetMapping(value = URLConstants.IS_COMMENT_ALREADY_REPORTED_BY_USER, produces = "application/json")
    @ResponseBody
    public String isCommentAlreadyReportedByUser(@PathVariable String commentId, @RequestParam String userUsername) {
        CommentServiceModel comment = this.commentServices.findById(commentId);
        UserServiceModel user = this.userServices.findUserByUsername(userUsername);

        if (comment == null || user == null) {
            return new Gson().toJson(false);
        }

        if (comment.getCreator().getUsername().equals(user.getUsername())) {
            return new Gson().toJson(true);
        }

        boolean isReported = this.commentReportServices.isReportedByUser(user, comment);
        return new Gson().toJson(isReported);
    }
}

package com.ichop.core.areas.report.web.controllers.api;

import com.google.gson.Gson;
import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.post.services.PostServices;
import com.ichop.core.areas.report.services.PostReportServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostReportApiController extends BaseController {

    private final PostServices postServices;
    private final PostReportServices postReportServices;
    private final UserServices userServices;

    @Autowired
    public PostReportApiController(PostServices postServices, PostReportServices postReportServices, UserServices userServices) {
        this.postServices = postServices;
        this.postReportServices = postReportServices;
        this.userServices = userServices;
    }

    @GetMapping(value = URLConstants.IS_POST_ALREADY_REPORTED_BY_USER, produces = "application/json")
    @ResponseBody
    public String isPostAlreadyReportedByUser(@PathVariable String postId, @RequestParam String userUsername) {
        PostServiceModel post = this.postServices.findById(postId);
        UserServiceModel user = this.userServices.findUserByUsername(userUsername);

        if (post == null || user == null) {
            return new Gson().toJson(false);
        }

        if (post.getCreator().getUsername().equals(user.getUsername())) {
            return new Gson().toJson(true);
        }

        boolean isReported = this.postReportServices.isReportedByUser(user, post);
        return new Gson().toJson(isReported);
    }
}

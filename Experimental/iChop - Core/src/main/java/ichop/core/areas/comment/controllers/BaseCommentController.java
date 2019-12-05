package ichop.core.areas.comment.controllers;

import ichop.core.areas.comment.constants.CommentRoutingConstants;
import ichop.core.areas.comment.requesters.BaseCommentRequester;
import ichop.core.areas.comment.requesters.ThreadCommentRequester;
import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.thread.requesters.ThreadRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseCommentController {

    private final ThreadRequester threadRequester;
    private final ThreadCommentRequester threadCommentRequester;
    private final BaseCommentRequester baseCommentRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public BaseCommentController(ThreadRequester threadRequester,
                                 ThreadCommentRequester threadCommentRequester,
                                 BaseCommentRequester baseCommentRequester,
                                 ResponseHelpers responseHelpers) {
        this.threadRequester = threadRequester;
        this.threadCommentRequester = threadCommentRequester;
        this.baseCommentRequester = baseCommentRequester;
        this.responseHelpers = responseHelpers;
    }

    @GetMapping(CommentRoutingConstants.CREATOR_TOTAL_COMMENTS)
    public ResponseEntity findCreatorTotal(@RequestParam String username) {
        JmsReplyModel reply = this.baseCommentRequester.totalComments(username);

        return this.responseHelpers.respondGeneric(reply);
    }
}

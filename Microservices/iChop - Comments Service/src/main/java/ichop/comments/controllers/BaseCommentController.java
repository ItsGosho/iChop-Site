package ichop.comments.controllers;

import ichop.comments.constants.CommentReplyConstants;
import ichop.comments.constants.CommentRoutingConstants;
import ichop.comments.services.GenericCommentServices;
import org.ichop.commons.domain.LongReply;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseCommentController {


    private final GenericCommentServices genericCommentServices;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public BaseCommentController(GenericCommentServices genericCommentServices, ResponseHelpers responseHelpers) {
        this.genericCommentServices = genericCommentServices;
        this.responseHelpers = responseHelpers;
    }

    @GetMapping(CommentRoutingConstants.CREATOR_TOTAL_COMMENTS)
    public ResponseEntity findCreatorTotal(@RequestParam String username) {
        Long total = this.genericCommentServices.findCreatorTotal(username);
        LongReply longReply = new LongReply(total);

        return this.responseHelpers.respondSuccessful(CommentReplyConstants.FETCH_SUCCESSFUL, longReply);
    }
}

package ichop.core.areas.comment.controllers;

import ichop.core.areas.comment.constants.CommentRoutingConstants;
import ichop.core.areas.comment.models.jms.create.ThreadCommentCreateRequest;
import ichop.core.areas.comment.requester.ThreadCommentRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ThreadCommentController {

    private final ThreadCommentRequester threadCommentRequester;

    @Autowired
    public ThreadCommentController(ThreadCommentRequester threadCommentRequester) {
        this.threadCommentRequester = threadCommentRequester;
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(CommentRoutingConstants.THREAD_CREATE)
    public ResponseEntity create(ThreadCommentCreateRequest request, Principal principal) {
        request.setCreatorUsername(principal.getName());

        return null;
    }

    @PreAuthorize("hasAuthority('MODERATOR') and @baseCommentRequesterImp.isCreator(#threadId,#principal.name,'THREAD') == true")
    @PostMapping(CommentRoutingConstants.THREAD_DELETE)
    public ResponseEntity delete(@PathVariable String threadId,@PathVariable String commentId, Principal principal) {

        return null;
    }

    @PostMapping(CommentRoutingConstants.THREAD_ALL)
    public ResponseEntity findBy(@RequestParam String threadId) {

        return null;
    }
}

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
    @PostMapping(CommentRoutingConstants.CREATE_THREAD)
    public ResponseEntity create(ThreadCommentCreateRequest request, Principal principal) {

        return null;
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(CommentRoutingConstants.DELETE_THREAD)
    public ResponseEntity delete(@PathVariable String id, Principal principal) {

        return null;
    }

    @PostMapping(CommentRoutingConstants.THREAD_FIND_BY)
    public ResponseEntity delete(@RequestParam String threadId) {

        return null;
    }
}

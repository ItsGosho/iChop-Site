package ichop.core.areas.comment.controllers;

import ichop.core.areas.comment.constants.CommentRoutingConstants;
import ichop.core.areas.comment.models.jms.create.ThreadCommentCreateRequest;
import ichop.core.areas.comment.requester.ThreadCommentRequester;
import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.thread.requester.ThreadRequester;
import org.ichop.commons.domain.JmsReplyModel;
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

    private final ThreadRequester threadRequester;
    private final ThreadCommentRequester threadCommentRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public ThreadCommentController(ThreadRequester threadRequester, ThreadCommentRequester threadCommentRequester, ResponseHelpers responseHelpers) {
        this.threadRequester = threadRequester;
        this.threadCommentRequester = threadCommentRequester;
        this.responseHelpers = responseHelpers;
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(CommentRoutingConstants.THREAD_CREATE)
    public ResponseEntity create(ThreadCommentCreateRequest request, Principal principal) {
        request.setCreatorUsername(principal.getName());

        JmsReplyModel threadReply = this.threadRequester.findById(request.getThreadId());

        if (threadReply.isSuccessful()) {
            return null;
        }

        return this.responseHelpers.respondGeneric(threadReply);
    }

    @PreAuthorize("hasAuthority('MODERATOR') and @baseCommentRequesterImp.isCreator(#threadId,#principal.name,'THREAD') == true")
    @PostMapping(CommentRoutingConstants.THREAD_DELETE)
    public ResponseEntity delete(@PathVariable String threadId, @PathVariable String commentId, Principal principal) {

        return null;
    }

    @PostMapping(CommentRoutingConstants.THREAD_ALL)
    public ResponseEntity findBy(@RequestParam String threadId) {

        return null;
    }
}

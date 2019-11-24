package ichop.core.areas.comment.controllers;

import ichop.core.areas.comment.constants.CommentRoutingConstants;
import ichop.core.areas.comment.models.CommentType;
import ichop.core.areas.comment.models.jms.create.ThreadCommentCreateRequest;
import ichop.core.areas.comment.requesters.BaseCommentRequester;
import ichop.core.areas.comment.requesters.ThreadCommentRequester;
import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.thread.requesters.ThreadRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class ThreadCommentController {

    private final ThreadRequester threadRequester;
    private final ThreadCommentRequester threadCommentRequester;
    private final BaseCommentRequester baseCommentRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public ThreadCommentController(ThreadRequester threadRequester,
                                   ThreadCommentRequester threadCommentRequester,
                                   BaseCommentRequester baseCommentRequester,
                                   ResponseHelpers responseHelpers) {
        this.threadRequester = threadRequester;
        this.threadCommentRequester = threadCommentRequester;
        this.baseCommentRequester = baseCommentRequester;
        this.responseHelpers = responseHelpers;
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(CommentRoutingConstants.THREAD_CREATE)
    public ResponseEntity create(@RequestBody ThreadCommentCreateRequest request, Principal principal) {
        request.setCreatorUsername(principal.getName());

        JmsReplyModel threadReply = this.threadRequester.findById(request.getThreadId());

        if (threadReply.isSuccessful()) {
            JmsReplyModel result = this.threadCommentRequester.create(request);
            return this.responseHelpers.respondGeneric(result);
        }

        return this.responseHelpers.respondGeneric(threadReply);
    }

    @PreAuthorize("hasAuthority('MODERATOR') or @baseCommentRequesterImp.isCreator(#commentId,authentication.principal,'THREAD') == true")
    @PostMapping(CommentRoutingConstants.THREAD_DELETE)
    public ResponseEntity delete(@PathVariable String threadId, @PathVariable String commentId, Principal principal) {

        JmsReplyModel threadReply = this.threadRequester.findById(threadId);

        if (threadReply.isSuccessful()) {
            JmsReplyModel result = this.baseCommentRequester.deleteById(commentId, CommentType.THREAD);
            return this.responseHelpers.respondGeneric(result);
        }

        return this.responseHelpers.respondGeneric(threadReply);
    }

    @GetMapping(CommentRoutingConstants.THREAD_ALL)
    public ResponseEntity findBy(@PathVariable String threadId) {

        JmsReplyModel threadReply = this.threadRequester.findById(threadId);

        if (threadReply.isSuccessful()) {
            JmsReplyModel result = this.threadCommentRequester.findByThreadId(threadId);
            return this.responseHelpers.respondGeneric(result);
        }

        return this.responseHelpers.respondGeneric(threadReply);
    }
}

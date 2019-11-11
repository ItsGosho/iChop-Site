package ichop.core.areas.reaction.controllers;

import ichop.core.areas.comment.requesters.ThreadCommentRequester;
import ichop.core.areas.reaction.constants.ReactionRoutingConstants;
import ichop.core.areas.reaction.models.ReactionOn;
import ichop.core.areas.reaction.models.jms.create.ReactionCreateRequest;
import ichop.core.areas.reaction.models.jms.find.ReactionsFindByRequest;
import ichop.core.areas.reaction.requesters.ReactionRequester;
import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.thread.requesters.ThreadRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ReactionController {

    private final ReactionRequester reactionRequester;
    private final ThreadRequester threadRequester;
    private final ThreadCommentRequester threadCommentRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public ReactionController(ReactionRequester reactionRequester,
                              ThreadRequester threadRequester,
                              ThreadCommentRequester threadCommentRequester,
                              ResponseHelpers responseHelpers) {
        this.reactionRequester = reactionRequester;
        this.threadRequester = threadRequester;
        this.threadCommentRequester = threadCommentRequester;
        this.responseHelpers = responseHelpers;
    }

    @PostMapping(ReactionRoutingConstants.CREATE)
    public ResponseEntity create(ReactionCreateRequest request, Principal principal) {
        request.setCreatorUsername(principal.getName());

        JmsReplyModel replyModel = this.reactionRequester.create(request);

        return this.responseHelpers.respondGeneric(replyModel);
    }

    @GetMapping(ReactionRoutingConstants.IS_REACTED)
    public ResponseEntity isReacted(@RequestParam String creatorUsername, @RequestParam String entityId, @RequestParam String reactionOn) {
        JmsReplyModel replyModel = this.reactionRequester.isReacted(creatorUsername, entityId, ReactionOn.valueOf(reactionOn));

        return this.responseHelpers.respondGeneric(replyModel);
    }

    @GetMapping(ReactionRoutingConstants.FIND_BY)
    public ResponseEntity findBy(ReactionsFindByRequest request) {
        JmsReplyModel replyModel = this.reactionRequester.findBy(request);

        return this.responseHelpers.respondGeneric(replyModel);
    }
}

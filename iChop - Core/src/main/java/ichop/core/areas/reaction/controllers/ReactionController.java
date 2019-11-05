package ichop.core.areas.reaction.controllers;

import ichop.core.areas.reaction.constants.ReactionRoutingConstants;
import ichop.core.areas.reaction.models.jms.create.ReactionCreateRequest;
import ichop.core.areas.reaction.requesters.ReactionRequester;
import ichop.core.areas.rest.helpers.ResponseHelpers;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ReactionController {

    private final ReactionRequester reactionRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public ReactionController(ReactionRequester reactionRequester, ResponseHelpers responseHelpers) {
        this.reactionRequester = reactionRequester;
        this.responseHelpers = responseHelpers;
    }

    @PostMapping(ReactionRoutingConstants.CREATE)
    public ResponseEntity create(ReactionCreateRequest request, Principal principal) {


        JmsReplyModel replyModel = this.reactionRequester.create(request);

        return this.responseHelpers.respondGeneric(replyModel);
    }
}

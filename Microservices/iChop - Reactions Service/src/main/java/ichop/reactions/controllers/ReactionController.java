package ichop.reactions.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reactions.constants.ReactionReplyConstants;
import ichop.reactions.constants.ReactionRoutingConstants;
import ichop.reactions.domain.entities.Reaction;
import ichop.reactions.domain.enums.EntityType;
import ichop.reactions.domain.models.jms.ReactionReply;
import ichop.reactions.domain.models.jms.check.ReactionIsReactedRequest;
import ichop.reactions.domain.models.jms.create.ReactionCreateRequest;
import ichop.reactions.domain.models.jms.find.ReactionsFindByRequest;
import ichop.reactions.domain.models.service.ReactionServiceModel;
import ichop.reactions.helpers.CriteriaHelper;
import ichop.reactions.services.ReactionServices;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReactionController {

    private final ReactionServices reactionServices;
    private final ResponseHelpers responseHelpers;
    private final ValidationHelper validationHelper;
    private final CriteriaHelper criteriaHelper;
    private final MongoTemplate mongoTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public ReactionController(ReactionServices reactionServices,
                              ResponseHelpers responseHelpers,
                              ValidationHelper validationHelper,
                              CriteriaHelper criteriaHelper,
                              MongoTemplate mongoTemplate,
                              ObjectMapper objectMapper) {
        this.reactionServices = reactionServices;
        this.responseHelpers = responseHelpers;
        this.validationHelper = validationHelper;
        this.criteriaHelper = criteriaHelper;
        this.mongoTemplate = mongoTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping(ReactionRoutingConstants.CREATE)
    public ResponseEntity create(@RequestBody ReactionCreateRequest request, Principal principal) {
        request.setCreatorUsername(principal.getName());

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        ReactionServiceModel reaction = this.objectMapper.convertValue(request, ReactionServiceModel.class);
        this.reactionServices.save(reaction);

        return this.responseHelpers.respondSuccessful(ReactionReplyConstants.REACTION_CREATED_SUCCESSFUL, reaction);
    }

    @GetMapping(ReactionRoutingConstants.IS_REACTED)
    public ResponseEntity isReacted(@RequestParam String creatorUsername, @RequestParam String entityId, @RequestParam String reactionOn) {
        ReactionIsReactedRequest request = new ReactionIsReactedRequest();
        request.setCreatorUsername(creatorUsername);
        request.setEntityId(entityId);
        request.setEntityType(EntityType.valueOf(reactionOn));

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        boolean isReacted = this.reactionServices.isReacted(creatorUsername, entityId, EntityType.valueOf(reactionOn));
        BoolReply reply = new BoolReply(isReacted);

        return this.responseHelpers.respondSuccessful(ReactionReplyConstants.REACTION_CHECK_SUCCESSFUL, reply);
    }

    @GetMapping(ReactionRoutingConstants.FIND_BY)
    public ResponseEntity findBy(ReactionsFindByRequest request) {

        Query query = this.criteriaHelper.createBy(request);
        List<ReactionReply> result = this.mongoTemplate.find(query, Reaction.class)
                .stream()
                .map(x -> this.objectMapper.convertValue(x, ReactionReply.class))
                .collect(Collectors.toList());

        return this.responseHelpers.respondSuccessful(ReactionReplyConstants.REACTION_CHECK_SUCCESSFUL, result);
    }
}

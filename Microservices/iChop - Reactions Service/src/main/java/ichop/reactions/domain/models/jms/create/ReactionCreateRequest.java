package ichop.reactions.domain.models.jms.create;

import ichop.reactions.domain.enums.EntityType;
import ichop.reactions.domain.enums.ReactionType;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@reactionServicesImp.isReacted(#this.creatorUsername,#this.entityId,#this.entityType) == false", message = "Already reacted!")
public class ReactionCreateRequest extends RequestCandidate {

    @NotNull
    private String creatorUsername;

    @NotNull
    private String entityId;

    @NotNull
    private EntityType entityType;

    @NotNull
    private ReactionType reactionType;
}

package ichop.reactions.domain.models.jms.create;

import ichop.reactions.common.domain.BaseRequestModel;
import ichop.reactions.common.validators.SpELValidation;
import ichop.reactions.domain.enums.EntityType;
import ichop.reactions.domain.enums.ReactionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@reactionServicesImp.hasReacted(#this.userId,#this.entityId,#this.entityType) == false", message = "Already reacted!")
public class ReactionCreateRequest extends BaseRequestModel {

    @NotNull
    private String userId;

    @NotNull
    private String entityId;

    @NotNull
    private EntityType entityType;

    @NotNull
    private ReactionType reactionType;

    /*TODO: reaction of userId,entityType and entityId to not exist!*/
}

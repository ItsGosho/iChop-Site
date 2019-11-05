package ichop.core.areas.reaction.models.jms.create;

import ichop.core.areas.reaction.models.ReactionOn;
import ichop.core.areas.reaction.models.ReactionType;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class ReactionCreateRequest extends RequestCandidate {

    private String userId;
    private String entityId;
    private ReactionOn entityType;
    private ReactionType reactionType;

}

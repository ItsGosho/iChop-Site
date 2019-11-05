package ichop.core.areas.reaction.models.jms.check;

import ichop.core.areas.reaction.models.ReactionOn;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class ReactionIsReactedRequest extends RequestCandidate {

    private String userId;
    private String entityId;
    private ReactionOn entityType;

}

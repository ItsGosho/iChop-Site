package ichop.core.areas.reaction.models.jms.check;

import ichop.core.areas.reaction.models.ReactionOn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReactionIsReactedRequest extends RequestCandidate {

    private String creatorUsername;
    private String entityId;
    private ReactionOn entityType;

}

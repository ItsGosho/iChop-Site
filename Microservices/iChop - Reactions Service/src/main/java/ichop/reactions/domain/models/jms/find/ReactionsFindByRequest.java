package ichop.reactions.domain.models.jms.find;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class ReactionsFindByRequest extends RequestCandidate {

    private String creatorUsername;
    private String entityId;
    private String entityType;
    private String reactionType;

}

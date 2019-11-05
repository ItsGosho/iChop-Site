package ichop.core.areas.reaction.requesters;

import ichop.core.areas.reaction.models.ReactionOn;
import ichop.core.areas.reaction.models.jms.create.ReactionCreateRequest;
import org.ichop.commons.domain.JmsReplyModel;

public interface ReactionRequester {
    JmsReplyModel create(ReactionCreateRequest request);

    boolean isReacted(String userId, String entityId, ReactionOn reactionOn);
}

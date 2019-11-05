package ichop.core.areas.reaction.models.jms;

import ichop.core.areas.reaction.models.ReactionOn;
import ichop.core.areas.reaction.models.ReactionType;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.ReplyCandidate;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReactionReply extends ReplyCandidate {

    private String id;
    private String userId;
    private String entityId;
    private ReactionOn entityType;
    private ReactionType reactionType;
    private LocalDateTime reactedOn;

}

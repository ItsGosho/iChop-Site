package ichop.reactions.domain.models.jms.create;

import ichop.reactions.common.domain.BaseReplyModel;
import ichop.reactions.domain.enums.EntityType;
import ichop.reactions.domain.enums.ReactionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReactionCreateReply extends BaseReplyModel {

    private String id;
    private String userId;
    private String entityId;
    private EntityType entityType;
    private ReactionType reactionType;
    private LocalDateTime reactedOn;

}

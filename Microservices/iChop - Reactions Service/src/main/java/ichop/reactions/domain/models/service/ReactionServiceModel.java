package ichop.reactions.domain.models.service;

import ichop.reactions.domain.enums.EntityType;
import ichop.reactions.domain.enums.ReactionType;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseServiceModel;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReactionServiceModel extends BaseServiceModel {

    private String creatorUsername;
    private String entityId;
    private EntityType entityType;
    private ReactionType reactionType;
    private LocalDateTime reactedOn;

}

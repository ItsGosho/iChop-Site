package ichop.reactions.domain.entities;

import ichop.reactions.common.domain.BaseEntity;
import ichop.reactions.domain.enums.EntityType;
import ichop.reactions.domain.enums.ReactionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class Reaction extends BaseEntity {

    @NotNull
    private String userId;

    @NotNull
    private String entityId;

    @NotNull
    private EntityType entityType;

    @NotNull
    private ReactionType reactionType;

    @NotNull
    private LocalDateTime reactedOn = LocalDateTime.now();

}

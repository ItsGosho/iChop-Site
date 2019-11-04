package ichop.reactions.domain.entities;

import ichop.reactions.domain.enums.EntityType;
import ichop.reactions.domain.enums.ReactionType;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.MongoEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class Reaction extends MongoEntity {

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

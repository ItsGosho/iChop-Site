package ichop.reactions.domain.models.jms.check;

import ichop.reactions.domain.enums.EntityType;
import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReactionIsReactedRequest extends RequestCandidate {

    @NotNull
    private String userId;

    @NotNull
    private String entityId;

    @NotNull
    private EntityType entityType;

}

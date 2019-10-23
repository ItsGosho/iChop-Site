package ichop.reactions.domain.models.jms.check;

import ichop.reactions.common.domain.BaseRequestModel;
import ichop.reactions.domain.enums.EntityType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReactionIsReactedRequest extends BaseRequestModel {

    @NotNull
    private String userId;

    @NotNull
    private String entityId;

    @NotNull
    private EntityType entityType;

}

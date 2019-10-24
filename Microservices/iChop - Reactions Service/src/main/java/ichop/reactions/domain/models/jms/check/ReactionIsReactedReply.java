package ichop.reactions.domain.models.jms.check;

import ichop.reactions.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionIsReactedReply extends BaseReplyModel {

    private Boolean isReacted;

    public ReactionIsReactedReply(Boolean isReacted) {
        this.isReacted = isReacted;
    }
}

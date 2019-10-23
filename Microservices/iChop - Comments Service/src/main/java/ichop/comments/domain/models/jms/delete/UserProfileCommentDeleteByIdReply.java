package ichop.comments.domain.models.jms.delete;

import ichop.comments.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileCommentDeleteByIdReply extends BaseReplyModel {

    public UserProfileCommentDeleteByIdReply(String message) {
        super(message);
    }
}

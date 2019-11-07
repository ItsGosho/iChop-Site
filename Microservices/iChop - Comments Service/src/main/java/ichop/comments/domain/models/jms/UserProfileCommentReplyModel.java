package ichop.comments.domain.models.jms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileCommentReplyModel extends BaseCommentReplyModel {

    private String userProfileUsername;

}

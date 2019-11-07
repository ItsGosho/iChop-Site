package ichop.core.areas.comment.models.jms.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileCommentCreateRequest extends CommentCreateRequest {

    private String userProfileUsername;

}

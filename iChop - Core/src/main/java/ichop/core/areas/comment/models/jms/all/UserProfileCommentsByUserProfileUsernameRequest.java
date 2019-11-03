package ichop.core.areas.comment.models.jms.all;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class UserProfileCommentsByUserProfileUsernameRequest extends RequestCandidate {

    private String userProfileUsername;

}

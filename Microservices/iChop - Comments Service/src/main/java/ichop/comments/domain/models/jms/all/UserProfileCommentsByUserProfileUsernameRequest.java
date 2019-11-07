package ichop.comments.domain.models.jms.all;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserProfileCommentsByUserProfileUsernameRequest extends RequestCandidate {

    @NotNull
    private String userProfileUsername;

}

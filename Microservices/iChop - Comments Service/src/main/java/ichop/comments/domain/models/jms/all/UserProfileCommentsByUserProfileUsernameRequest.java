package ichop.comments.domain.models.jms.all;

import ichop.comments.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserProfileCommentsByUserProfileUsernameRequest extends BaseRequestModel {

    @NotNull
    private String userProfileUsername;

}

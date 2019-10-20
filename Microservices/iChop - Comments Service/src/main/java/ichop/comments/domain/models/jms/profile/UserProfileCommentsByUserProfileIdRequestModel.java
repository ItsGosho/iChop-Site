package ichop.comments.domain.models.jms.profile;

import ichop.comments.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserProfileCommentsByUserProfileIdRequestModel extends BaseRequestModel {

    @NotNull
    private String userProfileId;

    /*TODO: validate that exists by userProfileId*/
}

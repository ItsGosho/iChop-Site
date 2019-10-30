package ichop.users.domain.models.jms.follow;

import ichop.users.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true",message = "User not found!")
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.followingUsername) == true",message = "Following not found!")*/
public class UserIsFollowingRequest extends BaseRequestModel {

    private String username;
    private String followingUsername;

}

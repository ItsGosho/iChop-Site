package ichop.users.domain.models.jms.follow;

import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true", message = "User not found!")
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.unfollowUsername) == true", message = "User to unfollow not found!")
@SpELValidation(value = "@userFollowServicesImp.isFollowed(#this.username,#this.followUsername) == true", message = "Not following!")
public class UserUnfollowRequest extends BaseRequestModel {

    private String username;
    private String unfollowUsername;

}

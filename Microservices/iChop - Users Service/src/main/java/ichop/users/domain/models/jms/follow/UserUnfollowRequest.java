package ichop.users.domain.models.jms.follow;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true", message = "User not found!")
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.unfollowUsername) == true", message = "User to unfollow not found!")
@SpELValidation(value = "@userFollowServicesImp.isFollowed(#this.username,#this.unfollowUsername) == true", message = "Not following!")
public class UserUnfollowRequest extends RequestCandidate {

    private String username;
    private String unfollowUsername;

}

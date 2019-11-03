package ichop.users.domain.models.jms.follow;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
/*@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true",message = "User not found!")
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.followingUsername) == true",message = "Following not found!")*/
public class UserIsFollowingRequest extends RequestCandidate {

    private String username;
    private String followingUsername;

}

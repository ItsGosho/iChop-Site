package ichop.users.domain.models.jms.follow;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true", message = "User not found!")
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.followUsername) == true", message = "User to follow not found!")
@SpELValidation(value = "#this.username != #this.followUsername", message = "You canno't follow yourself!")
@SpELValidation(value = "@userFollowServicesImp.isFollowed(#this.username,#this.followUsername) == false", message = "Already following!")
public class UserFollowRequest extends RequestCandidate {

    private String username;
    private String followUsername;

}

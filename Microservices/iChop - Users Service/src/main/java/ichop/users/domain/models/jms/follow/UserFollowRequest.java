package ichop.users.domain.models.jms.follow;

import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsById(#this.userId) == true", message = "User not found!")
@SpELValidation(value = "@userServicesImp.existsById(#this.userFollowId) == true", message = "User to follow not found!")
@SpELValidation(value = "@userFollowServicesImp.isFollowed(#this.username,#this.followUsername) == false", message = "Already following!")
public class UserFollowRequest extends BaseRequestModel {

    private String username;
    private String followUsername;

}

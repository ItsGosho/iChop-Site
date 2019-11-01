package ichop.users.domain.models.jms.role;

import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true",message = "User not found!")
@SpELValidation(value = "@userServicesImp.hasPreviousRole(#this.username) == true",message = "User doesn't have previous role!")
public class UserRoleDemoteRequest extends BaseRequestModel {

    private String username;

}

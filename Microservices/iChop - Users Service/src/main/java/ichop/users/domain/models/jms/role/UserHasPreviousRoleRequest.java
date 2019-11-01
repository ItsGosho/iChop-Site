package ichop.users.domain.models.jms.role;

import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true",message = "User not found!")
public class UserHasPreviousRoleRequest extends BaseRequestModel {

    private String username;

}

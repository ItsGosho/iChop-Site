package ichop.users.domain.models.jms.password.forgotten;

import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByEmail(#this.email) == true", message = "User not found!")
public class UserForgottenPasswordRequest extends BaseRequestModel {

    private String email;

}

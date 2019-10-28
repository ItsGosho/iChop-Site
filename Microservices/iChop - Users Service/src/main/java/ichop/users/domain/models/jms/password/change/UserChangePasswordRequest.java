package ichop.users.domain.models.jms.password.change;

import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByEmail(#this.email) == false", message = "User not found!")
@SpELValidation(value = "#this.password != #this.confirmPassword", message = "Passwords are not equal!")
public class UserChangePasswordRequest extends BaseRequestModel {

    private String email;

    @Length(min = 3, message = "Password must be at least 3 characters!")
    @Length(max = 50, message = "Password must be max 50 characters!")
    private String password;

    private String confirmPassword;

}

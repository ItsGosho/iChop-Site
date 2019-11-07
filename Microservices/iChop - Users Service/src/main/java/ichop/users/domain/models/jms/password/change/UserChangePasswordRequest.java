package ichop.users.domain.models.jms.password.change;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true", message = "User not found!")
@SpELValidation(value = "#this.password == #this.confirmPassword", message = "Passwords are not equal!")
public class UserChangePasswordRequest extends RequestCandidate {

    private String username;

    @Length(min = 3, message = "Password must be at least 3 characters!")
    @Length(max = 50, message = "Password must be max 50 characters!")
    private String password;

    private String confirmPassword;

}

package ichop.user.areas.user.domain.models.binding;

import ichop.user.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.isUserExistsByUsername(#this.username) == false", message = "Username is already present!")
@SpELValidation(value = "@userServicesImp.isUserExistsByEmail(#this.email) == false", message = "Email is already present!")
@SpELValidation(value = "#this.password == #this.confirmPassword", message = "Passwords doesn't match!")
public class UserRegisterBinding {

    @Length(min = 3,message = "Username must be at least 3 characters!")
    @Length(max = 25,message = "Username must be max 25 characters!")
    private String username;

    @Length(min = 3,message = "Password must be at least 3 characters!")
    @Length(max = 50,message = "Password must be max 50 characters!")
    private String password;

    private String confirmPassword;

    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            message = "Invalid email format!")
    @Length(max = 50,message = "Email must be max 50 characters!")
    private String email;

}

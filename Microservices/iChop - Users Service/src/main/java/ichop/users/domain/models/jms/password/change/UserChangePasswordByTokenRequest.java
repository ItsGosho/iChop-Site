package ichop.users.domain.models.jms.password.change;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

@Getter
@Setter
@SpELValidation(value = "@passwordTokenRequesterImp.isValid(#this.token)", message = "Token is invalid!")
@SpELValidation(value = "#this.password == #this.confirmPassword", message = "Passwords are not equal!")
public class UserChangePasswordByTokenRequest extends RequestCandidate {

    private String password;
    private String confirmPassword;
    private String token;

}

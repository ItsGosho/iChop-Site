package ichop.users.domain.models.jms.password.change;

import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SpELValidation(value = "@passwordTokenRequesterImp.isValid(#this.token)", message = "Token is invalid!")
@SpELValidation(value = "#this.password == #this.confirmPassword", message = "Passwords are not equal!")
public class UserChangePasswordByTokenRequest extends BaseRequestModel {

    private String password;
    private String confirmPassword;
    private String token;

}

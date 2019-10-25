package ichop.core.areas.user.domain.models.binding;

import ichop.core.areas.user.constants.UserValidationConstants;
import ichop.core.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.isUserExistsByUsername(#this.username) == false",message = "Username is already present!")
@SpELValidation(value = "@userServicesImp.isUserExistsByEmail(#this.email) == false",message = "Email is already present!")
public class UserRegisterBindingModel {

    @NotNull
    @NotEmpty
	@Length(min = UserValidationConstants.USERNAME_MIN_LENGTH)
	@Length(max = UserValidationConstants.USERNAME_MAX_LENGTH)
    private String username;

    @NotNull
    @NotEmpty
	@Length(max = UserValidationConstants.PASSWORD_MAX_LENGTH)
    private String password;

    @NotNull
    @NotEmpty
    private String confirmPassword;

    @NotNull
    @NotEmpty
    @Email(regexp = UserValidationConstants.EMAIL_PATTERN)
    @Length(max = UserValidationConstants.EMAIL_MAX_LENGTH)
	private String email;

}

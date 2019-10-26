package ichop.users.domain.models.binding;

import ichop.users.constants.UserValidationConstants;
import ichop.users.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserResetPasswordBinding {

    @NotNull
    @NotEmpty
    @Length(max = UserValidationConstants.PASSWORD_MAX_LENGTH)
    private String password;

    @NotNull
    @NotEmpty
    private String confirmPassword;

    @NotNull
    private UserServiceModel user;

}

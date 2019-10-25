package ichop.core.areas.user.domain.models.binding;

import ichop.core.areas.user.constants.UserValidationConstants;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserResetPasswordBindingModelByUser {

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
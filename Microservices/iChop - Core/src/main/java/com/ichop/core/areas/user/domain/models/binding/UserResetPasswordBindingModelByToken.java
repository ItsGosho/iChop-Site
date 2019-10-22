package com.ichop.core.areas.user.domain.models.binding;

import com.ichop.core.areas.user.constants.UserValidationConstants;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserResetPasswordBindingModelByToken {

    @NotNull
    @NotEmpty
    @Pattern(regexp = UserValidationConstants.PASSWORD_PATTERN)
    @Length(max = UserValidationConstants.PASSWORD_MAX_LENGTH)
    private String password;

    @NotNull
    @NotEmpty
    private String confirmPassword;

    @NotNull
    @NotEmpty
    private String token;

}

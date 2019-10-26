package ichop.core.areas.user.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserForgottenPasswordBinding {

    @NotNull
    @NotEmpty
    private String email;

}

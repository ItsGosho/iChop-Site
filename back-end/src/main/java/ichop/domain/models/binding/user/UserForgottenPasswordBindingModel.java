package ichop.domain.models.binding.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserForgottenPasswordBindingModel {

    @NotNull
    @NotEmpty
    private String usernameOrEmail;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }
}

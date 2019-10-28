package ichop.emails.domain.models.templates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailResetPasswordView {

    private String expirationDate;
    private String passwordResetUrl;

}

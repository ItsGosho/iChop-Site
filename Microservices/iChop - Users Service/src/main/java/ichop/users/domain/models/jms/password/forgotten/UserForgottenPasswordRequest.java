package ichop.users.domain.models.jms.password.forgotten;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByEmail(#this.email) == true", message = "User not found!")
public class UserForgottenPasswordRequest extends RequestCandidate {

    private String email;

}

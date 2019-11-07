package ichop.tokens.domain.models.jms.valid;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@passwordTokenServicesImp.isValid(#this.token) == true",message = "Token is invalid!")
public class PasswordTokenIsValidRequest extends RequestCandidate {

    @NotNull
    private String token;

}

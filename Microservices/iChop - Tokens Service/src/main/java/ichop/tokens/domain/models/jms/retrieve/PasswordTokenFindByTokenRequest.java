package ichop.tokens.domain.models.jms.retrieve;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

@Getter
@Setter
@SpELValidation(value = "@passwordTokenServicesImp.existsByToken(#this.token) == true",message = "Token doesn't exist!")
public class PasswordTokenFindByTokenRequest extends RequestCandidate {

    private String token;

}

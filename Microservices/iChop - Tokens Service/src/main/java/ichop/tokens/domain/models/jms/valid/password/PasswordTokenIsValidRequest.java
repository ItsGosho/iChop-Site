package ichop.tokens.domain.models.jms.valid.password;

import ichop.tokens.common.domain.BaseRequestModel;
import ichop.tokens.common.validators.SpELValidation;
import ichop.tokens.domain.enums.Type;
import ichop.tokens.validators.ExistsBy;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@passwordTokenServicesImp.isValid(#this.token) == true",message = "Token is invalid!")
public class PasswordTokenIsValidRequest extends BaseRequestModel {

    @NotNull
    private String token;

}

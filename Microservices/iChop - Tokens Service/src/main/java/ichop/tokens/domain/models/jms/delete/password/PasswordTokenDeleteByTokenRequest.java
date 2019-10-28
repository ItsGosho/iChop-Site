package ichop.tokens.domain.models.jms.delete.password;

import ichop.tokens.common.domain.BaseRequestModel;
import ichop.tokens.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SpELValidation(value = "@passwordTokenServicesImp.existsByToken(#this.token) == true", message = "Token doesn't exist!")
public class PasswordTokenDeleteByTokenRequest extends BaseRequestModel {

    private String token;

}

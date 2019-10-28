package ichop.tokens.domain.models.jms.retrieve.password;

import ichop.tokens.common.domain.BaseRequestModel;
import ichop.tokens.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SpELValidation(value = "@passwordTokenServicesImp.existsByToken(#this.token) == true",message = "Token doesn't exist!")
public class PasswordTokenGetUserByTokenRequest extends BaseRequestModel {

    private String token;

}

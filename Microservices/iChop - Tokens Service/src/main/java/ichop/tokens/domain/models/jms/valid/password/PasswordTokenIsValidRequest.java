package ichop.tokens.domain.models.jms.valid.password;

import ichop.tokens.common.domain.BaseRequestModel;
import ichop.tokens.domain.enums.Type;
import ichop.tokens.validators.ExistsBy;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PasswordTokenIsValidRequest extends BaseRequestModel {

    @NotNull
    @ExistsBy(type = Type.PASSWORD,field = "token",message = "Token doesn't exists!")
    private String token;

}

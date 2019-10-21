package ichop.tokens.domain.models.jms.password.valid;

import ichop.tokens.common.domain.BaseRequestModel;
import ichop.tokens.domain.enums.TokenType;
import ichop.tokens.validators.ExistsByToken;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PasswordTokenIsValidRequest extends BaseRequestModel {

    @NotNull
    @ExistsByToken(type = TokenType.PASSWORD)
    private String token;

}

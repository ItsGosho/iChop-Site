package ichop.tokens.domain.models.jms.password.valid;

import ichop.tokens.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PasswordTokenIsValidRequestModel extends BaseRequestModel {

    @NotNull
    private String token;

}

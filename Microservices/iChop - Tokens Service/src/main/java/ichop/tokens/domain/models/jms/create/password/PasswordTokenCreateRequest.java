package ichop.tokens.domain.models.jms.create.password;

import ichop.tokens.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PasswordTokenCreateRequest extends BaseRequestModel {

    @NotNull
    private String userId;

}

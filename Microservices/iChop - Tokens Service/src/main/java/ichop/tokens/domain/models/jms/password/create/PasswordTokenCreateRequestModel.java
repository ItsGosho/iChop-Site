package ichop.tokens.domain.models.jms.password.create;

import ichop.tokens.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PasswordTokenCreateRequestModel extends BaseRequestModel {

    @NotNull
    private String userId;

}

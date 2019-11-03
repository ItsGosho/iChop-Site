package ichop.tokens.domain.models.jms.create;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PasswordTokenCreateRequest extends RequestCandidate {

    @NotNull
    private String userUsername;

}

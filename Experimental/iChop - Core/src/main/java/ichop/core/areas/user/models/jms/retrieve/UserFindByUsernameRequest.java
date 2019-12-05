package ichop.core.areas.user.models.jms.retrieve;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFindByUsernameRequest extends RequestCandidate {

    @NotNull
    private String username;

}

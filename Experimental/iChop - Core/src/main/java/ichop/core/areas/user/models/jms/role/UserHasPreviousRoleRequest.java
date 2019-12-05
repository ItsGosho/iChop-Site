package ichop.core.areas.user.models.jms.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserHasPreviousRoleRequest extends RequestCandidate {

    private String username;

}

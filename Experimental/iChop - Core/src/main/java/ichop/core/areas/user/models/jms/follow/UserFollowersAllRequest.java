package ichop.core.areas.user.models.jms.follow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFollowersAllRequest extends RequestCandidate {

    private String username;

}

package ichop.core.areas.user.models.jms.retrieve;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class UsersAllPageableRequest extends RequestCandidate {

    private Pageable pageable;

}

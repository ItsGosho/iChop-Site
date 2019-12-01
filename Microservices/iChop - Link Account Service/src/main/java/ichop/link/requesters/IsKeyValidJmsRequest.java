package ichop.link.requesters;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class IsKeyValidJmsRequest extends RequestCandidate {

    private String linkKey;

}

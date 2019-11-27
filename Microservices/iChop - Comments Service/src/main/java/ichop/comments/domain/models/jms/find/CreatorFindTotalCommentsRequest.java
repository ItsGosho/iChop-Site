package ichop.comments.domain.models.jms.find;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class CreatorFindTotalCommentsRequest extends RequestCandidate {

    private String username;

}

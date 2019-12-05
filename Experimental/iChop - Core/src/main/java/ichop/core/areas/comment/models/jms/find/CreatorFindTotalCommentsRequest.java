package ichop.core.areas.comment.models.jms.find;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatorFindTotalCommentsRequest extends RequestCandidate {

    private String username;

}

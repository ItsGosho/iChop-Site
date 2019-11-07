package ichop.core.areas.comment.models.jms.all;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreadCommentsFindByThreadIdRequest extends RequestCandidate {

    private String threadId;
}

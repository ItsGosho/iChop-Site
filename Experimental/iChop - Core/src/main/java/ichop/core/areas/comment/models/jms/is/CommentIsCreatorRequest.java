package ichop.core.areas.comment.models.jms.is;

import ichop.core.areas.comment.models.CommentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentIsCreatorRequest extends RequestCandidate {

    private String id;
    private String creatorUsername;
    private CommentType type;
}

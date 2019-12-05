package ichop.core.areas.comment.models.jms.delete;

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
public class CommentDeleteByIdRequest extends RequestCandidate {

    private String id;
    private CommentType type;
}
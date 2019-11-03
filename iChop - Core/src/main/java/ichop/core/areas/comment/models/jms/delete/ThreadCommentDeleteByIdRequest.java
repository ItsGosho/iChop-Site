package ichop.core.areas.comment.models.jms.delete;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadCommentDeleteByIdRequest extends RequestCandidate {

    @NotNull
    private String id;

}

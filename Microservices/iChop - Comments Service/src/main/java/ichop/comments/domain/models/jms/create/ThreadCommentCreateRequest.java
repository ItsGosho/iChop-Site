package ichop.comments.domain.models.jms.create;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadCommentCreateRequest extends RequestCandidate {

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    private String creatorUsername;

    @NotNull
    private String threadId;
}

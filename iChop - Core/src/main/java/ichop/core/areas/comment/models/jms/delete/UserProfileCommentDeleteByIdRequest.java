package ichop.core.areas.comment.models.jms.delete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileCommentDeleteByIdRequest extends RequestCandidate {

    @NotNull
    private String id;
}

package ichop.comments.domain.models.jms.create;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.ichop.commons.domain.RequestCandidate;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserProfileCommentCreateRequest extends RequestCandidate {

    @NotNull
    @Length(min = 3,max = 150)
    private String content;

    @NotNull
    private String creatorUsername;

    @NotNull
    private String userProfileUsername;
}

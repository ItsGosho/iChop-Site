package ichop.comments.domain.models.jms.create;

import ichop.comments.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserProfileCommentCreateRequest extends BaseRequestModel {

    @NotNull
    @Length(min = 3,max = 150)
    private String content;

    @NotNull
    private String creatorUsername;

    @NotNull
    private String userProfileUsername;
}

package ichop.comments.domain.models.jms.create;

import ichop.comments.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadCommentCreateRequest extends BaseRequestModel {

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    private String creatorId;

    @NotNull
    private String threadId;
}

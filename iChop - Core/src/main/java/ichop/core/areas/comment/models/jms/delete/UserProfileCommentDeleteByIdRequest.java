package ichop.core.areas.comment.models.jms.delete;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserProfileCommentDeleteByIdRequest extends BaseRequestModel {

    @NotNull
    private String id;
}

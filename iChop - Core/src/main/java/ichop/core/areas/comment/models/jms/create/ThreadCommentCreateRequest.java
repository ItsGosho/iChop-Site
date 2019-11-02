package ichop.core.areas.comment.models.jms.create;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadCommentCreateRequest extends BaseRequestModel {

    private String content;
    private String creatorUsername;
    private String threadId;
}

package ichop.core.areas.comment.models.jms.all;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadCommentsFindByThreadIdRequest extends BaseRequestModel {

    private String threadId;
}

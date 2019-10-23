package ichop.comments.domain.models.jms.all;

import ichop.comments.common.domain.BaseRequestModel;
import ichop.comments.domain.enums.Type;
import ichop.comments.validators.ExistsBy;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadCommentsByThreadIdRequest extends BaseRequestModel {

    @NotNull
    @ExistsBy(type = Type.THREAD,field = "threadId",message = "Thread wasn't found!")
    private String threadId;
}

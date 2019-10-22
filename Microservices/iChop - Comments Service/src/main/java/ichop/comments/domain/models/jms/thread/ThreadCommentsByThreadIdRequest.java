package ichop.comments.domain.models.jms.thread;

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
    private String threadId;

    /*TODO: validate that exists by threadId*/
}

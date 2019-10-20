package ichop.comments.domain.models.jms.thread;

import ichop.comments.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadCommentsByThreadIdRequestModel extends BaseRequestModel {

    @NotNull
    private String threadId;

    /*TODO: validate that exists by threadId*/
}

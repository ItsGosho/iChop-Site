package ichop.comments.domain.models.jms.thread;

import ichop.comments.common.domain.BaseRequestModel;
import ichop.comments.domain.enums.Type;
import ichop.comments.validators.ExistsById;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadCommentDeleteByIdRequestModel extends BaseRequestModel {

    @NotNull
    @ExistsById(type = Type.THREAD)
    private String id;

    /*TODO: Where to validate the three cases MODERATOR or creator ?*/

}

package ichop.comments.domain.models.jms.profile;

import ichop.comments.common.domain.BaseRequestModel;
import ichop.comments.domain.enums.Type;
import ichop.comments.validators.ExistsById;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserProfileCommentDeleteByIdRequestModel extends BaseRequestModel {

    @NotNull
    @ExistsById(type = Type.USER_PROFILE)
    private String id;

    /*TODO: Where to validate the three cases MODERATOR or creator or onHisProfile ?*/

}

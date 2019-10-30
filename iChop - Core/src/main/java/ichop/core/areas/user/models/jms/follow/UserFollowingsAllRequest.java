package ichop.core.areas.user.models.jms.follow;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFollowingsAllRequest extends BaseRequestModel {

    private String username;

}

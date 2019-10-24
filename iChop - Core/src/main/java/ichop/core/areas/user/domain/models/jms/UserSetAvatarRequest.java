package ichop.core.areas.user.domain.models.jms;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSetAvatarRequest extends BaseRequestModel {

    private String username;
    private String avatar;

}

package ichop.users.domain.models.jms.follow;

import ichop.users.common.domain.BaseReplyModel;
import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import ichop.users.domain.models.service.UserServiceModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowersAllReply extends BaseReplyModel {

    private List<UserServiceModel> followers;

}

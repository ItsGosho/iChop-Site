package ichop.core.areas.user.models.jms.follow;

import ichop.core.areas.user.models.jms.retrieve.UserFindByEmailReply;
import ichop.core.common.domain.BaseReplyModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowingsAllReply extends BaseReplyModel {

    private List<UserFindByEmailReply> followings;

}
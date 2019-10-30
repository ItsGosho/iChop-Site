package ichop.core.areas.user.models.jms.follow;

import ichop.core.common.domain.BaseReplyModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserIsFollowingReply extends BaseReplyModel {

    private boolean isFollowing;

}

package ichop.core.areas.user.requester;

import org.ichop.commons.domain.JmsReplyModel;

public interface UserFollowRequester {
    JmsReplyModel isFollowing(String username, String followingUsername);

    JmsReplyModel follow(String username, String followUsername);

    JmsReplyModel unfollow(String username, String unfollowUsername);

    JmsReplyModel allFollowers(String username);

    JmsReplyModel allFollowings(String username);
}

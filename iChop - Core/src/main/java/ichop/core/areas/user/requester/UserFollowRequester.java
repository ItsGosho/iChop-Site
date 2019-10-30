package ichop.core.areas.user.requester;

import ichop.core.areas.user.models.jms.follow.*;

public interface UserFollowRequester {
    UserIsFollowingReply isFollowing(String username, String followingUsername);

    UserFollowReply follow(String username, String followUsername);

    UserUnfollowReply unfollow(String username, String unfollowUsername);

    UserFollowersAllReply allFollowers(String username);

    UserFollowingsAllReply allFollowings(String username);
}

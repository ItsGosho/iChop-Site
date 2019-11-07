package ichop.core.areas.user.requesters;

import ichop.core.areas.user.models.jms.follow.*;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserFollowRequesterImp implements UserFollowRequester {

    private final JmsHelper jmsHelper;

    private final String isFollowingDestination;
    private final String followDestination;
    private final String unfollowDestination;
    private final String allFollowersDestination;
    private final String allFollowingsDestination;

    @Autowired
    public UserFollowRequesterImp(JmsHelper jmsHelper,
                                  @Value("${artemis.queue.users.follow.is.following}") String isFollowingDestination,
                                  @Value("${artemis.queue.users.follow.follow}") String followDestination,
                                  @Value("${artemis.queue.users.follow.unfollow}") String unfollowDestination,
                                  @Value("${artemis.queue.users.follow.all.followers}") String allFollowersDestination,
                                  @Value("${artemis.queue.users.follow.all.followings}") String allFollowingsDestination) {
        this.jmsHelper = jmsHelper;

        this.isFollowingDestination = isFollowingDestination;
        this.followDestination = followDestination;
        this.unfollowDestination = unfollowDestination;
        this.allFollowersDestination = allFollowersDestination;
        this.allFollowingsDestination = allFollowingsDestination;
    }

    @Override
    public JmsReplyModel isFollowing(String username, String followingUsername) {
        UserIsFollowingRequest request = new UserIsFollowingRequest(username, followingUsername);

        return this.jmsHelper.sendAndReceive(this.isFollowingDestination, request);
    }

    @Override
    public JmsReplyModel follow(String username, String followUsername) {
        UserFollowRequest request = new UserFollowRequest(username, followUsername);

        return this.jmsHelper.sendAndReceive(this.followDestination, request);
    }

    @Override
    public JmsReplyModel unfollow(String username, String unfollowUsername) {
        UserUnfollowRequest request = new UserUnfollowRequest(username, unfollowUsername);

        return this.jmsHelper.sendAndReceive(this.unfollowDestination, request);
    }

    @Override
    public JmsReplyModel allFollowers(String username) {
        UserFollowersAllRequest request = new UserFollowersAllRequest(username);

        return this.jmsHelper.sendAndReceive(this.allFollowersDestination, request);
    }

    @Override
    public JmsReplyModel allFollowings(String username) {
        UserFollowingsAllRequest request = new UserFollowingsAllRequest(username);

        return this.jmsHelper.sendAndReceive(this.allFollowingsDestination, request);
    }

}

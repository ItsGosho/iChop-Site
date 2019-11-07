package ichop.users.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.domain.models.jms.UserReply;
import ichop.users.domain.models.jms.follow.*;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.services.UserFollowServices;
import ichop.users.services.UserServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.domain.EmptyReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.util.List;
import java.util.stream.Collectors;

import static ichop.users.constants.UserReplyConstants.*;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

@Component
public class UserFollowListeners extends BaseListener {

    private final UserServices userServices;
    private final UserFollowServices userFollowServices;

    @Autowired
    protected UserFollowListeners(JmsHelper jmsHelper,
                                  ObjectMapper objectMapper,
                                  UserServices userServices,
                                  UserFollowServices userFollowServices) {
        super(jmsHelper, objectMapper);
        this.userServices = userServices;
        this.userFollowServices = userFollowServices;
    }


    @JmsValidate(model = UserIsFollowingRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.follow.is.following}", containerFactory = QUEUE)
    public BoolReply isFollowing(Message message) {
        UserIsFollowingRequest requestModel = this.jmsHelper.toModel(message, UserIsFollowingRequest.class);


        UserServiceModel user = this.userServices.findByUsername(requestModel.getUsername());
        UserServiceModel following = this.userServices.findByUsername(requestModel.getFollowingUsername());

        boolean isFollowing = this.userFollowServices.isFollowed(user, following);

        return new BoolReply(isFollowing);
    }

    @JmsValidate(model = UserFollowRequest.class)
    @JmsAfterReturn(message = FOLLOW_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.follow.follow}", containerFactory = QUEUE)
    public EmptyReply follow(Message message) {
        UserFollowRequest requestModel = this.jmsHelper.toModel(message, UserFollowRequest.class);

        UserServiceModel user = this.userServices.findByUsername(requestModel.getUsername());
        UserServiceModel follow = this.userServices.findByUsername(requestModel.getFollowUsername());

        this.userFollowServices.follow(user, follow);

        return new EmptyReply();
    }

    @JmsValidate(model = UserUnfollowRequest.class)
    @JmsAfterReturn(message = UNFOLLOW_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.follow.unfollow}", containerFactory = QUEUE)
    public EmptyReply unFollow(Message message) {
        UserUnfollowRequest requestModel = this.jmsHelper.toModel(message, UserUnfollowRequest.class);

        UserServiceModel user = this.userServices.findByUsername(requestModel.getUsername());
        UserServiceModel unfollow = this.userServices.findByUsername(requestModel.getUnfollowUsername());

        this.userFollowServices.unfollow(user, unfollow);

        return new EmptyReply();
    }

    @JmsValidate(model = UserFollowersAllRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.follow.all.followers}", containerFactory = QUEUE)
    public List<UserReply> allFollowers(Message message) {
        UserFollowersAllRequest requestModel = this.jmsHelper.toModel(message, UserFollowersAllRequest.class);

        UserServiceModel user = this.userServices.findByUsername(requestModel.getUsername());
        List<UserServiceModel> followers = this.userFollowServices.getFollowers(user);

        return followers.stream().map(x -> this.objectMapper.convertValue(x, UserReply.class)).collect(Collectors.toList());
    }

    @JmsValidate(model = UserFollowingsAllRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.follow.all.followings}", containerFactory = QUEUE)
    public List<UserReply> allFollowings(Message message) {
        UserFollowingsAllRequest requestModel = this.jmsHelper.toModel(message, UserFollowingsAllRequest.class);

        UserServiceModel user = this.userServices.findByUsername(requestModel.getUsername());
        List<UserServiceModel> followings = this.userFollowServices.getFollowings(user);

        return followings.stream().map(x -> this.objectMapper.convertValue(x, UserReply.class)).collect(Collectors.toList());
    }

}

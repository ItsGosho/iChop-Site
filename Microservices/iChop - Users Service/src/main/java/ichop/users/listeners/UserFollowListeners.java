package ichop.users.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.common.aop.JmsAfterReturn;
import ichop.users.common.aop.JmsValidate;
import ichop.users.common.helpers.BaseListener;
import ichop.users.common.helpers.JmsHelper;
import ichop.users.domain.models.jms.follow.*;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.services.UserFollowServices;
import ichop.users.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.users.common.constants.JmsFactories.QUEUE;
import static ichop.users.constants.UserReplyConstants.*;

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
    public UserIsFollowingReply isFollowing(Message message) {
        UserIsFollowingRequest requestModel = this.jmsHelper.getResultModel(message, UserIsFollowingRequest.class);


        UserServiceModel user = this.userServices.find

        this.userFollowServices.isFollowed();

        return null;
    }

    @JmsValidate(model = UserFollowRequest.class)
    @JmsAfterReturn(message = FOLLOW_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.follow.follow}", containerFactory = QUEUE)
    public UserFollowReply follow(Message message) {
        UserFollowRequest requestModel = this.jmsHelper.getResultModel(message, UserFollowRequest.class);


        return null;
    }

    @JmsValidate(model = UserUnfollowRequest.class)
    @JmsAfterReturn(message = UNFOLLOW_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.follow.unfollow}", containerFactory = QUEUE)
    public UserUnfollowReply unFollow(Message message) {
        UserUnfollowRequest requestModel = this.jmsHelper.getResultModel(message, UserUnfollowRequest.class);


        return null;
    }

    @JmsValidate(model = UserFollowersAllRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.follow.all.followers}", containerFactory = QUEUE)
    public UserFollowersAllReply allFollowers(Message message) {
        UserFollowersAllRequest requestModel = this.jmsHelper.getResultModel(message, UserFollowersAllRequest.class);


        return null;
    }

    @JmsValidate(model = UserFollowingsAllRequest.class)
    @JmsAfterReturn(message = FETCHED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.users.follow.all.followings}", containerFactory = QUEUE)
    public UserFollowingsAllReply allFollowings(Message message) {
        UserFollowingsAllRequest requestModel = this.jmsHelper.getResultModel(message, UserFollowingsAllRequest.class);


        return null;
    }

}

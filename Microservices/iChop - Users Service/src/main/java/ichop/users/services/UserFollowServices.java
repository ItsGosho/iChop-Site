package ichop.users.services;

import ichop.users.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserFollowServices {


    void follow(UserServiceModel user, UserServiceModel userToFollow);

    void unfollow(UserServiceModel user, UserServiceModel userToUnfollow);

    boolean isAlreadyFollowedUser(UserServiceModel user, UserServiceModel followedUser);

    Long findTotalFollowings(UserServiceModel user);

    Long findTotalFollowers(UserServiceModel user);

    List<UserServiceModel> getFollowers(UserServiceModel user);

    List<UserServiceModel> getFollowings(UserServiceModel user);
}

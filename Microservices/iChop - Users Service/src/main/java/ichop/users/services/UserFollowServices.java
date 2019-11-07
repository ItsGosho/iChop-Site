package ichop.users.services;

import ichop.users.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserFollowServices {


    void follow(UserServiceModel user, UserServiceModel follow);

    void unfollow(UserServiceModel user, UserServiceModel follow);

    boolean isFollowed(UserServiceModel user, UserServiceModel follow);

    boolean isFollowed(String username, String followUsername);

    Long findTotalFollowings(UserServiceModel user);

    Long findTotalFollowers(UserServiceModel user);

    List<UserServiceModel> getFollowers(UserServiceModel user);

    List<UserServiceModel> getFollowings(UserServiceModel user);
}

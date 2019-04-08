package com.ichop.core.areas.user.services;

import com.ichop.core.areas.user.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserFollowServices {


    void follow(UserServiceModel user, UserServiceModel userToFollow);
    void unfollow(UserServiceModel user,UserServiceModel userToFollow);

    boolean isUserAlreadyFollowedUser(UserServiceModel user,UserServiceModel followedUser);

    int findUserTotalFollowings(UserServiceModel user);
    int findUserTotalFollowers(UserServiceModel user);

    List<UserServiceModel> getFollowers(UserServiceModel user);

    List<UserServiceModel> getFollowings(UserServiceModel user);

}

package ichop.core.areas.user.services;

import ichop.core.areas.user.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserFollowServices {


    /*
    *
    * Follow user
    * @param user is the user which will follow the @param userToFollow
    * @param userToFollow is the user which will be followed by @param user
    * @throws UserNotFoundException if the user or userToFollow are null
    * @throws UserCannotFollowException if the user tries to follow himself
    * @throws UserAlreadyFollowingHimException if the user is already follow him
    *
    * */
    void follow(UserServiceModel user, UserServiceModel userToFollow);

    /*
     *
     * Unfollow user
     * @param user is the user which will unfollow @param userToUnfollow
     * @param userToUnfollow is the user which will be unfollowed by @param user
     * @throws UserNotFoundException if the user or userToUnfollow are null
     * @throws UserNotFollowingHimException if the user is not following @param
     * userToUnfollow
     *
     * */
    void unfollow(UserServiceModel user,UserServiceModel userToUnfollow);

    /*
    *
    * Determines if the user is already following another user
    * @param user is the user which if the follower
    * @param followedUser is the user which is being followed
    * @throws UserNotFoundException if the user or the followed user are null
    *
    * */
    boolean isUserAlreadyFollowedUser(UserServiceModel user,UserServiceModel followedUser);

    /*
    *
    * Finds the total users that the @param user is following
    *
    * */
    int findUserTotalFollowings(UserServiceModel user);

    /*
    *
    * Finds the total users that are followings @param user
    *
    * */
    int findUserTotalFollowers(UserServiceModel user);

    /*
    *
    * Finds all users that are following @param user
    *
    * */
    List<UserServiceModel> getFollowers(UserServiceModel user);

    /*
    *
    * Finds all users that @param user is following
    *
    * */
    List<UserServiceModel> getFollowings(UserServiceModel user);

}

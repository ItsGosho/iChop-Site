import React from 'react';
import {
    setUserProfileFollow,
    setUserProfileInformation, setUserProfileMinecraft,
    setUserProfilePosts, setUserProfileReactions,
    setUserProfileUser
} from "../actions/user.profile.actions";


let userProfileInfoDispatchers = (dispatch) => {
    return {
        setUser: (user) => {
            dispatch(setUserProfileUser(user))
        },
        setFollow: (followings,followers) => {
            dispatch(setUserProfileFollow(followings,followers))
        },
        setPosts: (posts) => {
            dispatch(setUserProfilePosts(posts))
        },
        setInformation: (information) => {
            dispatch(setUserProfileInformation(information))
        },
        setMinecraft: (uuid,accountName) => {
            dispatch(setUserProfileMinecraft(uuid,accountName))
        },
        setReaction: (totalLikes,totalDislikes) => {
            dispatch(setUserProfileReactions(totalLikes,totalDislikes))
        },
    }
};

export default userProfileInfoDispatchers;
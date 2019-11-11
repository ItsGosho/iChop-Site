import React from 'react';
import {
    setUserProfileFollow,
    setUserProfileInformation, setUserProfileMinecraft,
    setUserProfilePosts, setUserProfileReactions,
    setUserProfileUser
} from "../actions/user.profile.actions";


let userProfileInfoDispatchers = (dispatch) => {
    return {
        fetchUser: (username) => {
            dispatch(setUserProfileUser(username))
        },
        fetchFollow: (username,profileViewerUsername) => {
            dispatch(setUserProfileFollow(username,profileViewerUsername))
        },
        fetchPosts: (username) => {
            dispatch(setUserProfilePosts(username))
        },
        fetchInformation: (username) => {
            dispatch(setUserProfileInformation(username))
        },
        fetchMinecraft: (username) => {
            dispatch(setUserProfileMinecraft(username))
        },
        fetchReactions: (username) => {
            dispatch(setUserProfileReactions(username))
        },
    }
};

export default userProfileInfoDispatchers;
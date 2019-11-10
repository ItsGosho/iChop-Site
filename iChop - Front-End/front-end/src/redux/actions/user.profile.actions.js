import Actions from "../../constants/actions.constants";


let setUserProfileUser = (user) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.SET_USER_PROFILE_USER,
            payload: {...user}
        });
    }
};

let setUserProfileFollow = (followings, followers) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.SET_USER_PROFILE_FOLLOW,
            payload: {followings, followers}
        });
    }
};

let setUserProfilePosts = (posts) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.SET_USER_PROFILE_POSTS,
            payload: {posts}
        });
    }
};

let setUserProfileInformation = (information) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.SET_USER_PROFILE_INFORMATION,
            payload: {...information}
        });
    }
};

let setUserProfileMinecraft = (uuid, accountName) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.SET_USER_PROFILE_MINECRAFT,
            payload: {
                minecraftUUID: uuid,
                minecraftAccountName: accountName
            }
        });
    }
};

let setUserProfileReactions = (totalLikes,totalDislikes) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.SET_USER_PROFILE_REACTIONS,
            payload: {totalLikes,totalDislikes}
        });
    }
};

export {
    setUserProfileUser,
    setUserProfileFollow,
    setUserProfilePosts,
    setUserProfileInformation,
    setUserProfileMinecraft,
    setUserProfileReactions
}
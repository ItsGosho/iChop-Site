import Actions from "../../constants/actions.constants";
import UserServices from "../../services/user.services";
import CommentServices from "../../services/comment.services";
import ReactionServices from "../../services/reaction.services";
import ReactionType from "../../constants/reaction.types.constants";


let setUserProfileUser = (username) => {
    return async (dispatch) => {
        let user = await UserServices.findByUsername(username);

        dispatch({
            type: Actions.SET_USER_PROFILE_USER,
            payload: {...user}
        });
    }
};

let setUserProfileFollow = (username,profileViewerUsername) => {
    return async (dispatch) => {
        let followings = await UserServices.findFollowings(username);
        let followers = await UserServices.findFollowers(username);
        let isViewerFollowingHim = await UserServices.isFollowing(profileViewerUsername,username);
        let isViewerFollowedByHim = await UserServices.isFollowing(username,profileViewerUsername);

        dispatch({
            type: Actions.SET_USER_PROFILE_FOLLOW,
            payload: {followings, followers,isViewerFollowingHim,isViewerFollowedByHim}
        });
    }
};

let setUserProfilePosts = (username) => {
    return async (dispatch) => {
        let posts = await CommentServices.findAllUserProfileComments(username);

        dispatch({
            type: Actions.SET_USER_PROFILE_POSTS,
            payload: {posts}
        });
    }
};

let setUserProfileInformation = (username) => {
    return async (dispatch) => {
        let information = await UserServices.findInformation(username);

        dispatch({
            type: Actions.SET_USER_PROFILE_INFORMATION,
            payload: {...information}
        });
    }
};

let setUserProfileMinecraft = (username) => {
    return async (dispatch) => {
        let uuid = undefined;
        let accountName = undefined;

        dispatch({
            type: Actions.SET_USER_PROFILE_MINECRAFT,
            payload: {
                minecraftUUID: uuid,
                minecraftAccountName: accountName
            }
        });
    }
};

let setUserProfileReactions = (username) => {
    return async (dispatch) => {
        let totalLikes = await ReactionServices.findByCreatorUsernameAndReactionType(username,ReactionType.LIKE).length;
        let totalDislikes = await ReactionServices.findByCreatorUsernameAndReactionType(username,ReactionType.DISLIKE).length;

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
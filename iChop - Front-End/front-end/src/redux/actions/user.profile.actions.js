import Actions from "../../constants/redux/actions.constants";
import UserServices from "../../services/user.services";
import CommentServices from "../../services/comment.services";
import ReactionServices from "../../services/reaction.services";
import ReactionType from "../../constants/enums/reaction.types.constants";


let setUserProfileUser = (username) => {
    return async (dispatch) => {
        let user = await UserServices.findByUsername(username);

        dispatch({
            type: Actions.USER_PROFILE_SET_USER,
            payload: {...user}
        });
    }
};

let setUserProfileFollow = (username, profileViewerUsername) => {
    return async (dispatch) => {
        let followings = await UserServices.findFollowings(username);
        let followers = await UserServices.findFollowers(username);
        let isViewerFollowingHim = false;
        let isViewerFollowedByHim = false;

        if (profileViewerUsername !== '') {
            isViewerFollowingHim = await UserServices.isFollowing(profileViewerUsername, username);
            isViewerFollowedByHim = await UserServices.isFollowing(username, profileViewerUsername);
        }

        dispatch({
            type: Actions.USER_PROFILE_SET_FOLLOW,
            payload: {followings, followers, isViewerFollowingHim, isViewerFollowedByHim}
        });
    }
};

let setUserProfilePosts = (username) => {
    return async (dispatch) => {
        let posts = await CommentServices.findAllUserProfileComments(username);

        dispatch({
            type: Actions.USER_PROFILE_SET_POSTS,
            payload: {posts}
        });
    }
};

let setUserProfileInformation = (username) => {
    return async (dispatch) => {
        let information = await UserServices.findInformation(username);

        dispatch({
            type: Actions.USER_PROFILE_SET_INFORMATION,
            payload: {...information}
        });
    }
};

let setUserProfileMinecraft = (username) => {
    return async (dispatch) => {
        let uuid = undefined;
        let accountName = undefined;

        dispatch({
            type: Actions.USER_PROFILE_SET_MINECRAFT,
            payload: {
                minecraftUUID: uuid,
                minecraftAccountName: accountName
            }
        });
    }
};

let setUserProfileReactions = (username) => {
    return async (dispatch) => {
        let likes = await ReactionServices.findByCreatorUsernameAndReactionType(username, ReactionType.LIKE);
        let dislikes = await ReactionServices.findByCreatorUsernameAndReactionType(username, ReactionType.DISLIKE);

        dispatch({
            type: Actions.USER_PROFILE_SET_REACTIONS,
            payload: {
                totalLikes: likes.length,
                totalDislikes: dislikes.length,
            }
        });
    }
};

let clearUserProfile = () => {
    return async (dispatch) => {

        dispatch({
            type: Actions.USER_PROFILE_CLEAR,
        });
    }
};

export {
    setUserProfileUser,
    setUserProfileFollow,
    setUserProfilePosts,
    setUserProfileInformation,
    setUserProfileMinecraft,
    setUserProfileReactions,
    clearUserProfile
}
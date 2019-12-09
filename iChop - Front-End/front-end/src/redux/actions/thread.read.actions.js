import Actions from "../../constants/redux/actions.constants";
import ThreadServices from "../../services/thread.services";
import CommentServices from "../../services/comment.services";
import ReactionServices from "../../services/reaction.services";
import LinkAccountServices from "../../services/link_account.services";


let fetchById = (id) => {
    return async (dispatch) => {
        let thread = await ThreadServices.findById(id);

        dispatch({
            type: Actions.THREAD_READ_SET_THREAD,
            payload: {...thread}
        });

        dispatch(fetchThreadCreatorInformation(thread.creatorUsername));
        dispatch(fetchThreadComments(thread.id));
        dispatch(fetchThreadStatisticsInformation(thread.id));
    }
};

let fetchThreadCreatorInformation = (username) => {
    return async (dispatch) => {
        let totalComments = await CommentServices.findCreatorTotalComments(username);
        let {playerName, playerUUID} = await LinkAccountServices.retrieveLink(username);

        dispatch({
            type: Actions.THREAD_READ_SET_CREATOR_INFO,
            payload: {
                creatorTotalComments: totalComments,
                creatorMinecraftUsername: playerName,
                creatorMinecraftUUID: playerUUID
            }
        });
    }
};

let fetchThreadStatisticsInformation = (id) => {
    return async (dispatch) => {
        let reactions = await ReactionServices.findAllById(id, 'THREAD');

        dispatch({
            type: Actions.THREAD_READ_SET_STATISTICS,
            payload: {totalReactions: reactions.length}
        });
    }
};

let fetchThreadComments = (id) => {
    return async (dispatch) => {
        let comments = await CommentServices.findAllThreadComments(id);

        dispatch({
            type: Actions.THREAD_READ_SET_COMMENTS,
            payload: {comments}
        });

        if(comments){
            for (const comment of comments) {
                CommentServices.findCreatorTotalComments(comment.creatorUsername)
                    .then((result) => {
                        dispatch({
                            type: Actions.THREAD_READ_SET_COMMENT_CREATOR_INFO,
                            payload: {
                                id: comment.id,
                                totalComments: result
                            }
                        });
                    });

                let likes = await ReactionServices.findAllByIdAndReactionType(comment.id, 'THREAD_COMMENT', 'LIKE');
                let dislikes = await ReactionServices.findAllByIdAndReactionType(comment.id, 'THREAD_COMMENT', 'DISLIKE');

                dispatch({
                    type: Actions.THREAD_READ_SET_COMMENT_STATISTICS,
                    payload: {
                        id: comment.id,
                        likes,
                        dislikes
                    }
                });
            }
        }
    }
};

export {
    fetchById,
    fetchThreadComments
}
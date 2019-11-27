import Actions from "../../constants/redux/actions.constants";
import ThreadServices from "../../services/thread.services";
import CommentServices from "../../services/comment.services";
import ReactionServices from "../../services/reaction.services";


let fetchAllPageable = (page, size) => {
    return async (dispatch) => {
        let threads = await ThreadServices.getAllPageable(page, size);

        dispatch({
            type: Actions.THREADS_ALL_SET,
            payload: {threads}
        });

        for (const thread of threads) {
            let id = thread.id;
            let reactions = await ReactionServices.findAllById(id, 'THREAD');
            let comments = await CommentServices.findAllThreadComments(id);

            dispatch(setTotalStatisticsForThreadById(id, 0, reactions.length, comments.length))
        }
    }
};

let setTotalStatisticsForThreadById = (id, totalViews, totalReactions, totalComments) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.THREADS_ALL_SET_STATISTICS,
            payload: {id, totalViews, totalReactions, totalComments}
        });
    }
};

let fetchTotal = () => {
    return async (dispatch) => {
        let total = await ThreadServices.getTotal();

        dispatch({
            type: Actions.THREADS_ALL_SET_TOTAL,
            payload: {total}
        });
    }
};

let removeFromAllById = (id) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.THREADS_ALL_REMOVE_BY_ID,
            payload: {id}
        });
    }
};

export {
    fetchAllPageable,
    fetchTotal,
    removeFromAllById
}
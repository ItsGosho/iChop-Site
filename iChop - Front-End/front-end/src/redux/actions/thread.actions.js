import Actions from "../../constants/redux/actions.constants";
import ThreadServices from "../../services/thread.services";
import CommentServices from "../../services/comment.services";
import ReactionServices from "../../services/reaction.services";


let fetchAllPageable = (page, size) => {
    return async (dispatch) => {
        let threads = await ThreadServices.getAllPageable(page, size);

        dispatch({
            type: Actions.FETCH_ALL_THREADS,
            payload: {threads}
        });

        for (const thread of threads) {
            let id = thread.id;
            let reactions = await ReactionServices.findAllById(id,'THREAD');
            console.log(reactions);
            let comments = await CommentServices.findAllThreadComments(id);
        }
    }
};

let fetchTotal = () => {
    return async (dispatch) => {
        let total = await ThreadServices.getTotal();

        dispatch({
            type: Actions.FETCH_TOTAL_THREADS,
            payload: {total}
        });
    }
};

let removeFromAllById = (id) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.REMOVE_FROM_ALL_THREADS_BY_ID,
            payload: {id}
        });
    }
};

export {
    fetchAllPageable,
    fetchTotal,
    removeFromAllById
}
import {
    addComment,
    fetchById,
    increaseReactions
} from "../actions/thread.read.actions";

let threadReadDispatchers = (dispatch) => {
    return {
        fetchThreadById: (id) => {
            dispatch(fetchById(id))
        },
        increaseReactions: () => {
            dispatch(increaseReactions())
        },
        addComment: (comment) => {
            dispatch(addComment(comment))
        }
    }
};

export default threadReadDispatchers;
import {
    fetchById, fetchThreadComments,
} from "../actions/thread.read.actions";

let threadReadDispatchers = (dispatch) => {
    return {
        fetchThreadById: (id) => {
            dispatch(fetchById(id))
        },
        fetchThreadComments: (id) => {
            dispatch(fetchThreadComments(id));
        }
    }
};

export default threadReadDispatchers;
import {
    fetchById,
} from "../actions/thread.read.actions";

let threadReadDispatchers = (dispatch) => {
    return {
        fetchThreadById: (id) => {
            dispatch(fetchById(id))
        }
    }
};

export default threadReadDispatchers;
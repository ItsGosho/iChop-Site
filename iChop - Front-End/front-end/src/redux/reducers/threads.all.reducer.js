import Actions from "../../constants/redux/actions.constants";

let initialState = {
    threads: [],
    total: 0
};


let result;
let threadsAllReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.FETCH_ALL_THREADS:
            let {threads} = action.payload;

            return Object.assign({}, state, {threads});

        case Actions.FETCH_TOTAL_THREADS:
            let {total} = action.payload;

            return Object.assign({}, state, {total});

        case Actions.SET_ALL_THREADS_TOTAL_STATISTICS_BY_ID: {
            let {id, totalViews, totalReactions, totalComments} = action.payload;

            result = [];

            for (const thread of state.threads) {
                if (thread.id === id) {
                    thread.totalViews = totalViews;
                    thread.totalReactions = totalReactions;
                    thread.totalComments = totalComments;
                }
                result.push(thread);
            }

        }
            return Object.assign({}, state, {threads: result});

        case Actions.REMOVE_FROM_ALL_THREADS_BY_ID: {
            let {id} = action.payload;

            result = [];

            for (const thread of state.threads) {
                if (thread.id !== id) {
                    result.push(thread);
                }
            }

        }
            return Object.assign({}, state, {threads: result});

        default:
            return state;

    }
};

export default threadsAllReducer;
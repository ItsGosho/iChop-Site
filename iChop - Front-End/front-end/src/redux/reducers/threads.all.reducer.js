import Actions from "../../constants/redux/actions.constants";

let initialState = {
    threads: [],
    total: 0
};


let threadsAllReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.FETCH_ALL_THREADS:
            let {threads} = action.payload;

            return Object.assign({}, state, {threads});

        case Actions.FETCH_TOTAL_THREADS:
            let {total} = action.payload;

            return Object.assign({}, state, {total});

        case Actions.REMOVE_FROM_ALL_THREADS_BY_ID:
            let {id} = action.payload;
            let newThreads = [];

            for (const thread of state.threads) {
                if (thread.id !== id) {
                    newThreads.push(thread);
                }
            }

            return Object.assign({}, state, {threads: newThreads});

        default:
            return state;

    }
};

export default threadsAllReducer;
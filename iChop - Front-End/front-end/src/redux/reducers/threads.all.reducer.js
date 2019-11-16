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

        default:
            return state;

    }
};

export default threadsAllReducer;
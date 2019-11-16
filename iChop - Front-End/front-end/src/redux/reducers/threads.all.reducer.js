import Actions from "../../constants/redux/actions.constants";

let initialState = {
    threads: []
};


let threadsAllReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.FETCH_ALL_THREADS:
            let {threads} = action.payload;

            return Object.assign({}, state, {threads});

        default:
            return state;

    }
};

export default threadsAllReducer;
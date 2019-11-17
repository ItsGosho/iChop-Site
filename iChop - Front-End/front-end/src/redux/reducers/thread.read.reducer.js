import Actions from "../../constants/redux/actions.constants";

let initialState = {
    id: '',
    creator: {
        username: '',
        totalComments: 0
    },
    title: '',
    content: '',
    createdOn: new Date(),
    views: 0,

    reactions: [],
    comments: []
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
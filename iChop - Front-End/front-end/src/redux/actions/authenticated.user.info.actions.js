import Actions from "../../constants/redux/actions.constants";


let set = (user) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.SET_AUTHENTICATED_USER_INFO,
            payload: {
                user: user
            }
        });
    }
};

let remove = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.REMOVE_AUTHENTICATED_USER_INFO
        });
    }
};

export {
    set,
    remove
}
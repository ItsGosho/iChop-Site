import Actions from "../../constants/actions.constants";


let login = (user) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.SET_AUTHENTICATED_USER_INFO,
            payload: {
                user: user
            }
        });
    }
};

let logout = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.REMOVE_AUTHENTICATED_USER_INFO
        });
    }
};

export {
    login,
    logout
}
import Actions from "../../constants/actions.constants";


let setAuthenticatedUserInfoAction = (user) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.SET_AUTHENTICATED_USER_INFO,
            payload: {
                user: user
            }
        });
    }
};

let removeAuthenticatedUserInfoAction = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.REMOVE_AUTHENTICATED_USER_INFO
        });
    }
};

export {
    setAuthenticatedUserInfoAction,
    removeAuthenticatedUserInfoAction
}
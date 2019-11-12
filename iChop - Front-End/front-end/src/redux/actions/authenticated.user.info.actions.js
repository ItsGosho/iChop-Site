import Actions from "../../constants/redux/actions.constants";
import UserServices from "../../services/user.services";


let fetchAuthenticatedUserInfo = () => {
    return async (dispatch) => {
        let user = await UserServices.retrieveUserByToken();

        if (user) {
            dispatch({
                type: Actions.SET_AUTHENTICATED_USER_INFO,
                payload: {
                    user: user
                }
            });
        }
    }
};

let removeAuthenticatedUserInfo = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.REMOVE_AUTHENTICATED_USER_INFO
        });
    }
};

export {
    fetchAuthenticatedUserInfo,
    removeAuthenticatedUserInfo
}
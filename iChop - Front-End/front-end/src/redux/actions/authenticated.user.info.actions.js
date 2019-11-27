import Actions from "../../constants/redux/actions.constants";
import UserServices from "../../services/user.services";


let fetchAuthenticatedUserInfo = () => {
    return async (dispatch) => {
        let user = await UserServices.retrieveUserByToken();

        if (user) {
            let information = await UserServices.findInformation(user.username);

            if (user) {
                dispatch({
                    type: Actions.AUTHENTICATED_USER_INFO_SET,
                    payload: {
                        user: user,
                        information: information
                    }
                });
            }
        }
    }
};

let removeAuthenticatedUserInfo = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.AUTHENTICATED_USER_INFO_REMOVE
        });
    }
};

export {
    fetchAuthenticatedUserInfo,
    removeAuthenticatedUserInfo
}
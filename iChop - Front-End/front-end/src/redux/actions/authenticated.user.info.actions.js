import Actions from "../../constants/redux/actions.constants";
import UserServices from "../../services/user.services";
import LinkAccountServices from "../../services/link_account.services";


let fetchAuthenticatedUser = () => {
    return async (dispatch) => {
        let user = await UserServices.retrieveUserByToken();

        dispatch(setUserInfo(user));
        dispatch(setPlayerInfo(user));
    }
};

let setUserInfo = (user) => {
    return async (dispatch) => {
        if (user) {
            let information = await UserServices.findInformation(user.username);

            if (user) {
                dispatch({
                    type: Actions.AUTHENTICATED_USER_INFO_SET,
                    payload: {user: user, information: information,}
                });
            }
        }
    }
};

let setPlayerInfo = (user) => {
    return async (dispatch) => {
        if (user) {
            let {playerName, playerUUID} = await LinkAccountServices.retrieveLink(user.username);

            dispatch({
                type: Actions.AUTHENTICATED_USER_INFO_SET,
                payload: {minecraft: {playerName, playerUUID}}
            });
        }
    }
};

let removeAuthenticatedInfo = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.AUTHENTICATED_USER_REMOVE
        });
    }
};

export {
    fetchAuthenticatedUser,
    removeAuthenticatedInfo
}
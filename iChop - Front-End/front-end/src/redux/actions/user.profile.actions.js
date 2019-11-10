import Actions from "../../constants/actions.constants";


let setProfileInfo = (user) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.SET_USER_PROFILE_INFO,
            payload: {
                user: user
            }
        });
    }
};

export {
    setProfileInfo
}
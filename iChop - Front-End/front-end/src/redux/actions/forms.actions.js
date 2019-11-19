import Actions from "../../constants/redux/actions.constants";


let showGuestDropdown = (toShow) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.NAVBAR_GUEST_SHOW_DROPDOWN,
            payload: {
                isDropdownShow: toShow
            }
        });
    }
};


let showCreateComment = (toShow) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.THREAD_READ_SHOW_CREATE_COMMENT,
            payload: {
                isCreateCommentShow: toShow
            }
        });
    }
};

let selectGuestLogin = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.NAVBAR_GUEST_SELECT_LOGIN
        });
    }
};

let selectGuestRegister = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.NAVBAR_GUEST_SELECT_REGISTER
        });
    }
};

let selectGuestForgottenPassword = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.NAVBAR_GUEST_SELECT_FORGOTTEN_PASSWORD
        });
    }
};

export {
    showGuestDropdown,
    showCreateComment,
    selectGuestLogin,
    selectGuestRegister,
    selectGuestForgottenPassword,
}
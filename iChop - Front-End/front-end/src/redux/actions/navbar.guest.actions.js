import Actions from "../../constants/actions.constants";


let navbarGuestShowDropdownAction = (toShow) => {
    return async (dispatch) => {
        dispatch({
            type: Actions.NAVBAR_GUEST_SHOW_DROPDOWN,
            payload: {
                showDropdown: toShow
            }
        });
    }
};

let navbarGuestSelectLoginAction = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.NAVBAR_GUEST_SELECT_LOGIN
        });
    }
};

let navbarGuestSelectRegisterAction = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.NAVBAR_GUEST_SELECT_REGISTER
        });
    }
};

let navbarGuestSelectForgottenPasswordAction = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.NAVBAR_GUEST_SELECT_FORGOTTEN_PASSWORD
        });
    }
};

export {
    navbarGuestShowDropdownAction,
    navbarGuestSelectLoginAction,
    navbarGuestSelectRegisterAction,
    navbarGuestSelectForgottenPasswordAction,
}
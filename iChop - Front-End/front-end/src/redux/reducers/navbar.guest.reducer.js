import Actions from "../../constants/actions.constants";

let initialState = {
    showDropdown: true,
    isLoginSelected: true,
    isRegisterSelected: false,
    isForgottenPasswordSelected: false,
};


let navbarGuestReducer = (state = initialState, action) => {

    switch (action.type) {
        case Actions.NAVBAR_GUEST_SHOW_DROPDOWN:
            return Object.assign({}, state, {
                showDropdown: action.showDropdown
            });
        case Actions.NAVBAR_GUEST_SELECT_LOGIN:
            return Object.assign({}, state, {
                isLoginSelected: true,
                isRegisterSelected: false,
                isForgottenPasswordSelected: false,
            });
        case Actions.NAVBAR_GUEST_SELECT_REGISTER:
            return Object.assign({}, state, {
                isLoginSelected: false,
                isRegisterSelected: true,
                isForgottenPasswordSelected: false
            });
        case Actions.NAVBAR_GUEST_SELECT_FORGOTTEN_PASSWORD:
            return Object.assign({}, state, {
                isLoginSelected: false,
                isRegisterSelected: false,
                isForgottenPasswordSelected: true
            });
        default:
            return state;

    }
};

export default navbarGuestReducer;
import Actions from "../../constants/redux/actions.constants";

let initialState = {
    isDropdownShow: false,
    isLoginSelected: true,
    isRegisterSelected: false,
    isForgottenPasswordSelected: false,

    isCreateCommentShow: false,
};


let formsReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.NAVBAR_GUEST_SHOW_DROPDOWN:
            return Object.assign({}, state, {
                isDropdownShow: action.payload.isDropdownShow
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


        case Actions.THREAD_READ_SHOW_CREATE_COMMENT:
            return Object.assign({}, state, {
                isCreateCommentShow: action.payload.isCreateCommentShow
            });

        default:
            return state;

    }
};

export default formsReducer;
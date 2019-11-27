import React from 'react';
import {
    selectGuestForgottenPassword,
    selectGuestLogin,
    selectGuestRegister, showCreateComment, showGuestDropdown
} from "../actions/forms.actions";

let formsDispatchers = (dispatch) => {
    return {
        showGuestDropdown: (toShow) => {
            dispatch(showGuestDropdown(toShow))
        },
        showCreateComment: (toShow) => {
            dispatch(showCreateComment(toShow))
        },
        selectGuestLogin: () => {
            dispatch(selectGuestLogin())
        },
        selectGuestRegister: () => {
            dispatch(selectGuestRegister())
        },
        selectGuestForgottenPassword: () => {
            dispatch(selectGuestForgottenPassword())
        },
    }
};

export default formsDispatchers;
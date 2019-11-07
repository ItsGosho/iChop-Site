import React from 'react';
import {
    navbarGuestSelectForgottenPasswordAction,
    navbarGuestSelectLoginAction,
    navbarGuestSelectRegisterAction, navbarGuestShowDropdownAction
} from "../actions/navbar.guest.actions";

let navbarGuestDispatchers = (dispatch) => {
    return {
        showDropdown: (toShow) => {
            dispatch(navbarGuestShowDropdownAction(toShow))
        },
        selectLogin: () => {
            dispatch(navbarGuestSelectLoginAction())
        },
        selectRegister: () => {
            dispatch(navbarGuestSelectRegisterAction())
        },
        selectForgottenPassword: () => {
            dispatch(navbarGuestSelectForgottenPasswordAction())
        },
    }
};

export default navbarGuestDispatchers;
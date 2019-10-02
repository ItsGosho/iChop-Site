import React from 'react';
import {connect} from "react-redux";
import {
    navbarGuestSelectForgottenPasswordAction,
    navbarGuestSelectLoginAction,
    navbarGuestSelectRegisterAction, navbarGuestShowDropdownAction
} from "../actions/navbar.guest.actions";

let props = (state) => {
    return {redux: state.navbarGuest};
};

let dispatcher = (dispatch) => {
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

let navbarGuestReduxHoc = (Comp) => {
    return connect(props, dispatcher)((props) => (<Comp {...props}/>));
};

export default navbarGuestReduxHoc;
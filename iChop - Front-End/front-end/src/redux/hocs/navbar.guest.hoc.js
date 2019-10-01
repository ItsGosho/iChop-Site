import React from 'react';
import {calculatorClear, calculatorDecrement, calculatorIncrement} from "../actions/calculator.actions";
import {connect} from "react-redux";
import Actions from "../actions/action.constants";
import {
    navbarGuestSelectForgottenPasswordAction,
    navbarGuestSelectLoginAction,
    navbarGuestSelectRegisterAction
} from "../actions/navbar.guest.actions";

let props = (state) => {
    let {isLoginSelected, isRegisterSelected, isForgottenPasswordSelected} = state.navbarGuest;

    return {isLoginSelected, isRegisterSelected, isForgottenPasswordSelected};
};

let dispatcher = (dispatch) => {
    return {
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

export default (Comp) => {
    return connect(props, dispatcher)((props) => (<Comp {...props}/>));
};
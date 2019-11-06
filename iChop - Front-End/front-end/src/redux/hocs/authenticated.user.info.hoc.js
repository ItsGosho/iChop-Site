import React from 'react';
import {connect} from "react-redux";
import {setAuthenticatedUserInfoAction,removeAuthenticatedUserInfoAction} from "../actions/authenticated.user.actions";

let props = (state) => {
    return {redux: state.authenticatedUserInfo};
};

let dispatcher = (dispatch) => {
    return {
        set: (user) => {
            dispatch(setAuthenticatedUserInfoAction(user))
        },
        remove: () => {
            dispatch(removeAuthenticatedUserInfoAction())
        },
    }
};

let authenticatedUserInfoHoc = (Comp) => {
    return connect(props, dispatcher)((props) => (<Comp {...props}/>));
};

export default authenticatedUserInfoHoc;
import React from 'react';
import {connect} from "react-redux";
import {login,logout} from "../actions/user.actions";

let props = (state) => {
    return {redux: state.authenticatedUserInfo};
};

let dispatcher = (dispatch) => {
    return {
        set: (user) => {
            dispatch(login(user))
        },
        remove: () => {
            dispatch(logout())
        },
    }
};

let authenticatedUserInfoHoc = (Comp) => {
    return connect(props, dispatcher)((props) => (<Comp {...props}/>));
};

export default authenticatedUserInfoHoc;
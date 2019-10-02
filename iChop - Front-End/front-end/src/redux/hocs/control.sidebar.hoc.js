import React from 'react';
import {connect} from "react-redux";
import {controlSidebarSelectHome, controlSidebarSelectRoleManagement} from "../actions/control.sidebar.actions";

let props = (state) => {
    return {redux: state.controlSidebar}
};

let dispatcher = (dispatch) => {
    return {
        selectHome: () => {
            dispatch(controlSidebarSelectHome())
        },
        selectRoleManagement: () => {
            dispatch(controlSidebarSelectRoleManagement())
        },
    }
};

let controlSidebarReduxHoc = (Comp) => {
    return connect(props, dispatcher)((props) => (<Comp {...props}/>));
};

export default controlSidebarReduxHoc;
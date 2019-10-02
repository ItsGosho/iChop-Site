import React from 'react';
import {connect} from "react-redux";
import {controlSidebarSelectInformation, controlSidebarSelectRoleManagement} from "../actions/control.sidebar.actions";
import {
    optionsSidebarSelectChangePassword,
    optionsSidebarSelectInformation,
    optionsSidebarSelectMinecraft
} from "../actions/options.sidebar.actions";

let props = (state) => {
    return {redux: state.optionsSidebar}
};

let dispatcher = (dispatch) => {
    return {
        selectInformation: () => {
            dispatch(optionsSidebarSelectInformation())
        },
        selectChangePassword: () => {
            dispatch(optionsSidebarSelectChangePassword())
        },
        selectMinecraft: () => {
            dispatch(optionsSidebarSelectMinecraft())
        },
    }
};

let optionsSidebarReduxHoc = (Comp) => {
    return connect(props, dispatcher)((props) => (<Comp {...props}/>));
};

export default optionsSidebarReduxHoc;
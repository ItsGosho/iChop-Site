import Actions from "../../constants/actions.constants";


let optionsSidebarSelectInformation = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.OPTIONS_SIDEBAR_SELECT_INFORMATION,
        });
    }
};

let optionsSidebarSelectChangePassword = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.OPTIONS_SIDEBAR_SELECT_CHANGE_PASSWORD,
        });
    }
};

let optionsSidebarSelectMinecraft = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.OPTIONS_SIDEBAR_SELECT_MINECRAFT,
        });
    }
};


export {
    optionsSidebarSelectInformation,
    optionsSidebarSelectChangePassword,
    optionsSidebarSelectMinecraft
}
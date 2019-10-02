import Actions from "../../constants/actions.constants";


let controlSidebarSelectInformation = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.CONTROL_SIDEBAR_SELECT_INFORMATION,
        });
    }
};

let controlSidebarSelectRoleManagement = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.CONTROL_SIDEBAR_SELECT_ROLE_MANAGEMENT,
        });
    }
};

export {
    controlSidebarSelectInformation,
    controlSidebarSelectRoleManagement
}
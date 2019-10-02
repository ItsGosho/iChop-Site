import Actions from "../../constants/actions.constants";


let controlSidebarSelectHome = () => {
    return async (dispatch) => {
        dispatch({
            type: Actions.CONTROL_SIDEBAR_SELECT_HOME,
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
    controlSidebarSelectHome,
    controlSidebarSelectRoleManagement
}
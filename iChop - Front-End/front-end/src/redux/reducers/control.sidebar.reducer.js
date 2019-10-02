import Actions from "../../constants/actions.constants";

let initialState = {
    isHomeSelected: true,
    isRoleManagementSelected: false,
};


let controlSidebarReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.CONTROL_SIDEBAR_SELECT_HOME:
            return Object.assign({}, state, {
                isHomeSelected: true,
                isRoleManagementSelected: false
            });

        case Actions.CONTROL_SIDEBAR_SELECT_ROLE_MANAGEMENT:
            return Object.assign({}, state, {
                isHomeSelected: false,
                isRoleManagementSelected: true
            });

        default:
            return state;

    }
};

export default controlSidebarReducer;
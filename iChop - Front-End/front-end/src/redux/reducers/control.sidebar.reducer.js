import Actions from "../../constants/actions.constants";

let initialState = {
    isInformationSelected: true,
    isRoleManagementSelected: false,
};


let controlSidebarReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.CONTROL_SIDEBAR_SELECT_INFORMATION:
            return Object.assign({}, state, {
                isInformationSelected: true,
                isRoleManagementSelected: false
            });

        case Actions.CONTROL_SIDEBAR_SELECT_ROLE_MANAGEMENT:
            return Object.assign({}, state, {
                isInformationSelected: false,
                isRoleManagementSelected: true
            });

        default:
            return state;

    }
};

export default controlSidebarReducer;
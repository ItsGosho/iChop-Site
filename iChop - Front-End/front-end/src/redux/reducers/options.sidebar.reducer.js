import Actions from "../../constants/actions.constants";

let initialState = {
    isInformationSelected: true,
    isChangePasswordSelected: false,
    isMinecraftSelected: false,
};


let optionsSidebarReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.OPTIONS_SIDEBAR_SELECT_INFORMATION:
            return Object.assign({}, state, {
                isInformationSelected: true,
                isChangePasswordSelected: false,
                isMinecraftSelected: false,
            });

        case Actions.OPTIONS_SIDEBAR_SELECT_CHANGE_PASSWORD:
            return Object.assign({}, state, {
                isInformationSelected: false,
                isChangePasswordSelected: true,
                isMinecraftSelected: false,
            });

        case Actions.OPTIONS_SIDEBAR_SELECT_MINECRAFT:
            return Object.assign({}, state, {
                isInformationSelected: false,
                isChangePasswordSelected: false,
                isMinecraftSelected: true,
            });

        default:
            return state;

    }
};

export default optionsSidebarReducer;
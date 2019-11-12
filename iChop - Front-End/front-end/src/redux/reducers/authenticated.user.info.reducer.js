import Actions from "../../constants/redux/actions.constants";
import Roles from "../../constants/enums/roles.constants";

let initialState = {
    id: '',
    username: '',
    email: '',
    authority: Roles.GUEST,
    registrationDate: new Date(),
    lastOnline: null,
    location: null,

    statusMessage: undefined,
    birthDate: undefined,
    aboutYou: undefined,
};


let authenticatedUserInfoReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.SET_AUTHENTICATED_USER_INFO:
            let {user,information} = action.payload;

            return Object.assign({}, state, {...user,...information});

        case Actions.REMOVE_AUTHENTICATED_USER_INFO:
            return {...initialState};

        default:
            return state;

    }
};

export default authenticatedUserInfoReducer;
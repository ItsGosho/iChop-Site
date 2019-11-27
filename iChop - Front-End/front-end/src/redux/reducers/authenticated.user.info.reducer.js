import Actions from "../../constants/redux/actions.constants";
import Roles from "../../constants/enums/roles.constants";

let initialState = {
    id: '',
    username: '',
    email: '',
    authority: Roles.GUEST,
    authorities: [],
    registrationDate: new Date(),
    lastOnline: null,
    location: null,

    statusMessage: undefined,
    birthDate: undefined,
    aboutYou: undefined,

    isAuthenticated: false
};


let authenticatedUserInfoReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.AUTHENTICATED_USER_INFO_SET:
            let {user,information} = action.payload;

            return Object.assign({}, state, {...user,...information,isAuthenticated: true});

        case Actions.AUTHENTICATED_USER_INFO_REMOVE:
            return {...initialState};

        default:
            return state;

    }
};

export default authenticatedUserInfoReducer;
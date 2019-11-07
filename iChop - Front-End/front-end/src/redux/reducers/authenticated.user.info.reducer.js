import Actions from "../../constants/actions.constants";
import Roles from "../../constants/roles.constants";

let initialState = {
    id: '',
    username: '',
    email: '',
    authority: Roles.GUEST,
    registrationDate: new Date(),
    lastOnline: new Date(),
    location: ''
};


let authenticatedUserInfoReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.SET_AUTHENTICATED_USER_INFO:
            let {id, username, email, authority, registrationDate, lastOnline, location} = action.payload.user;

            return Object.assign({}, state, {
                id,
                username,
                email,
                authority,
                registrationDate,
                lastOnline,
                location
            });

        case Actions.REMOVE_AUTHENTICATED_USER_INFO:
            return {...initialState};

        default:
            return state;

    }
};

export default authenticatedUserInfoReducer;
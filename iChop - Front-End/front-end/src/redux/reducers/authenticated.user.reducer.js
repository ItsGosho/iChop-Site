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


let authenticatedUserReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.SET_AUTHENTICATED_USER:
            let {id, username, email, authority, registrationDate, lastOnline, location} = action.payload;

            return Object.assign({}, state, {
                id,
                username,
                email,
                authority,
                registrationDate,
                lastOnline,
                location
            });

        case Actions.REMOVE_AUTHENTICATED_USER:
            return {...initialState};

        default:
            return state;

    }
};

export default navbarGuestReducer;
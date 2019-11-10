import Actions from "../../constants/actions.constants";

let initialState = {

    /*USER*/
    id: null,
    username: null,
    email: null,
    authority: null,
    registrationDate: null,
    lastOnline: null,
    location: null,

    totalMessages: 0,
    profileComments: [],

    aboutYou: null,
    birthday: null,

    totalFollowing: 0,
    totalFollowers: 0,
    followings: [],
    followers: [],

    totalLikes: 0,
    totalDislikes: 0,

    minecraftUUID: '',
    minecraftAccountName: '',
};


let userProfileInfoReducer = (state = initialState, action) => {

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

export default userProfileInfoReducer;
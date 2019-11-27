import Actions from "../../constants/redux/actions.constants";

let initialState = {
    id: null,
    username: null,
    email: null,
    authority: null,
    registrationDate: null,
    lastOnline: null,
    location: null,

    totalMessages: 0,
    profileComments: [],

    statusMessage: null,
    birthDate: null,
    aboutYou: null,

    followings: [],
    followers: [],

    isViewerFollowingHim: false,
    isViewerFollowedByHim: false,

    totalLikes: 0,
    totalDislikes: 0,

    minecraftUUID: '',
    minecraftAccountName: '',
};


let userProfileInfoReducer = (state = initialState, action) => {

    switch (action.type) {

        case Actions.USER_PROFILE_SET_USER:
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

        case Actions.USER_PROFILE_SET_FOLLOW:
            let {followings, followers,isViewerFollowingHim,isViewerFollowedByHim} = action.payload;

            return Object.assign({}, state, {
                followings,
                followers,
                isViewerFollowingHim,
                isViewerFollowedByHim,
            });

        case Actions.USER_PROFILE_SET_POSTS:
            let {posts} = action.payload;

            return Object.assign({}, state, {posts});

        case Actions.USER_PROFILE_SET_INFORMATION:
            let {statusMessage, birthDate, aboutYou} = action.payload;

            return Object.assign({}, state, {statusMessage, birthDate, aboutYou});

        case Actions.USER_PROFILE_SET_MINECRAFT:
            let {minecraftUUID, minecraftAccountName} = action.payload;

            return Object.assign({}, state, {minecraftUUID, minecraftAccountName});

        case Actions.USER_PROFILE_SET_REACTIONS:
            let {totalLikes, totalDislikes} = action.payload;

            return Object.assign({}, state, {totalLikes, totalDislikes});

        case Actions.USER_PROFILE_CLEAR:
            return Object.assign({}, state, initialState);

        default:
            return state;

    }
};

export default userProfileInfoReducer;
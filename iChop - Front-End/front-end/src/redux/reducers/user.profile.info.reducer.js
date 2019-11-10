import Actions from "../../constants/actions.constants";

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

        case Actions.SET_USER_PROFILE_USER:
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

        case Actions.SET_USER_PROFILE_FOLLOW:
            let {followings, followers} = action.payload;

            return Object.assign({}, state, {
                followings,
                followers,
                totalFollowing: followings.length,
                totalFollowers: followers.length,
            });

        case Actions.SET_USER_PROFILE_POSTS:
            let {posts} = action.payload;

            return Object.assign({}, state, {posts});

        case Actions.SET_USER_PROFILE_INFORMATION:
            let {aboutYou, birthday} = action.payload;

            return Object.assign({}, state, {aboutYou, birthday});

        case Actions.SET_USER_PROFILE_MINECRAFT:
            let {minecraftUUID, minecraftAccountName} = action.payload;

            return Object.assign({}, state, {minecraftUUID, minecraftAccountName});

        case Actions.SET_USER_PROFILE_REACTIONS:
            let {totalLikes, totalDislikes} = action.payload;

            return Object.assign({}, state, {totalLikes, totalDislikes});

        default:
            return state;

    }
};

export default userProfileInfoReducer;
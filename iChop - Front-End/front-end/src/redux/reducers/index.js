import navbarGuestReducer from "./navbar.guest.reducer";
import authenticatedUserInfoReducer from "./authenticated.user.info.reducer";
import userProfileInfoReducer from "./user.profile.info.reducer";
import threadsAllReducer from "./threads.all.reducer";

export default {
    navbarGuest: navbarGuestReducer,
    authenticatedUserInfo: authenticatedUserInfoReducer,
    userProfileInfo: userProfileInfoReducer,
    threadsAll: threadsAllReducer,
}
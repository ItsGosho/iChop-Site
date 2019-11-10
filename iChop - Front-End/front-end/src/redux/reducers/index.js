import navbarGuestReducer from "./navbar.guest.reducer";
import authenticatedUserInfoReducer from "./authenticated.user.info.reducer";
import userProfileInfoReducer from "./user.profile.info.reducer";

export default {
    navbarGuest: navbarGuestReducer,
    authenticatedUserInfo: authenticatedUserInfoReducer,
    userProfileInfo: userProfileInfoReducer
}
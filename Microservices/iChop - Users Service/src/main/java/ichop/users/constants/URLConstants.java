package ichop.users.constants;


public final class URLConstants {

    //UserController ------------------------------------------------------------------------------------------

    public static final String USER_PROFILE = "/user/{username}/profile"; /*EVERYONE*/
    public static final String USER_ALL = "/user/all"; /*ADMIN++*/

    //UserFollowController ------------------------------------------------------------------------------------

    public static final String USER_FOLLOW = "/user/{username}/follow"; /*USER++*/
    public static final String USER_UNFOLLOW = "/user/{username}/unfollow"; /*USER++*/
    public static final String USER_ALL_FOLLOWERS = "/api/user/{username}/all/followers"; /*EVERYONE*/
    public static final String USER_ALL_FOLLOWINGS = "/api/user/{username}/all/followings"; /*EVERYONE*/

    //UserControlController -----------------------------------------------------------------------------------

    public static final String USER_CONTROL_BASE = "/user/{username}/control"; /*ADMIN++*/
    public static final String USER_CONTROL_ROLE_MANAGEMENT = "/user/{username}/control/role-management"; /*ADMIN++*/
    public static final String USER_CONTROL_ROLE_MANAGEMENT_PROMOTE_USER = "/user/{username}/control/role-management/promote"; /*ADMIN++*/


    //UserMyProfileController ---------------------------------------------------------------------------------

    public static final String USER_PROFILE_OPTIONS_INFORMATION = "/user/my-profile/options/information"; /*Only LOGGED-IN USER IN HIS PROFILE*/
    public static final String USER_PROFILE_OPTIONS_CHANGE_PASSWORD = "/user/my-profile/options/change-password"; /*Only LOGGED-IN USER IN HIS PROFILE*/
    public static final String USER_PROFILE_OPTIONS_MINECRAFT = "/user/my-profile/options/minecraft"; /*Only LOGGED-IN USER IN HIS PROFILE*/

    //UserAuthenticationController ----------------------------------------------------------------------------

    public static final String USER_LOGIN = "/login"; /*GUEST*/
    public static final String USER_REGISTER = "/register"; /*GUEST*/
    public static final String USER_RESET_PASSWORD = "/user/reset/password"; /*EVERYONE*/

    //UserAuthorizationController ----------------------------------------------------------------------------

    public static final String UNAUTHORIZED = "/unauthorized"; /*EVERYONE*/

    //UserRestController -------------------------------------------------------------------------------------

    public static final String USER_SEND_EMAIL_RESET_PASSWORD = "/user/email/send/reset/password"; /*EVERYONE*/
    public static final String USER_EXISTS = "/api/user/exists"; /*EVERYONE*/
    public static final String IS_USER_FOLLOWING_USER = "/api/user/is-following"; /*EVERYONE*/

}

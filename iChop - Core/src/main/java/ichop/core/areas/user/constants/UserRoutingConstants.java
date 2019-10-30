package ichop.core.areas.user.constants;

public final class UserRoutingConstants {

    public static final String LOGIN = "/login"; /*GUEST*/
    public static final String REGISTER = "/register"; /*GUEST*/

    public static final String CHANGE_PASSWORD = "/user/change/password"; /*USER++*/
    public static final String FORGOTTEN_PASSWORD = "/user/forgotten/password"; /*USER++*/
    public static final String CHANGE_PASSWORD_BY_TOKEN = "/user/change/password/by/token"; /*GUEST++*/

    public static final String ALL = "/users/all"; /*ADMIN++*/

    /*TODO:*/

    //UserFollowController ------------------------------------------------------------------------------------

    public static final String FOLLOW = "/user/{username}/follow"; /*USER++*/
    public static final String UNFOLLOW = "/user/{username}/unfollow"; /*USER++*/
    public static final String ALL_FOLLOWERS = "/user/{username}/all/followers"; /*EVERYONE*/
    public static final String ALL_FOLLOWINGS = "/user/{username}/all/followings"; /*EVERYONE*/
    public static final String IS_FOLLOWING = "/user/{username}/is/following"; /*EVERYONE*/

    //UserInformationController -----------------------------------------------------------------------------------

    public static final String UPDATE_INFORMATION = "/user/{username}/information/update"; /*LOGGED IN USER IN HIS PROFILE OR OWNER*/
    public static final String RETRIEVE_INFORMATION = "/user/{username}/information/retrieve"; /*EVERYONE*/


    /*TODO:*/
    //UserControlController -----------------------------------------------------------------------------------

    public static final String CONTROL_BASE = "/user/{username}/control"; /*ADMIN++*/
    public static final String CONTROL_ROLE_MANAGEMENT = "/user/{username}/control/role-management"; /*ADMIN++*/
    public static final String CONTROL_ROLE_MANAGEMENT_PROMOTE_USER = "/user/{username}/control/role-management/promote"; /*ADMIN++*/


    //UserMyProfileController ---------------------------------------------------------------------------------

    public static final String PROFILE_OPTIONS_INFORMATION = "/user/my-profile/options/information"; /*Only LOGGED-IN USER IN HIS PROFILE*/
    public static final String PROFILE_OPTIONS_CHANGE_PASSWORD = "/user/my-profile/options/change-password"; /*Only LOGGED-IN USER IN HIS PROFILE*/
    public static final String PROFILE_OPTIONS_MINECRAFT = "/user/my-profile/options/minecraft"; /*Only LOGGED-IN USER IN HIS PROFILE*/

}

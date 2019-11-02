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

    //AdminController -----------------------------------------------------------------------------------

    public static final String FIND_BY = "/admin/user/find/by"; /*ADMIN++*/

    public static final String ROLE_PROMOTE = "/admin/user/{username}/role/promote"; /*ADMIN++*/
    public static final String ROLE_DEMOTE = "/admin/user/{username}/role/demote"; /*ADMIN++*/
    public static final String ROLE_HAS_NEXT = "/admin/user/{username}/role/has/next"; /*ADMIN++*/
    public static final String ROLE_HAS_PREVIOUS = "/admin/user/{username}/role/has/previous"; /*ADMIN++*/

}

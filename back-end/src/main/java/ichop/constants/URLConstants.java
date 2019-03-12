package ichop.constants;


public final class URLConstants {

    //HomeController ------------------------------------------------------------------------------------------

    public static final String HOME_GET = "/"; /*EVERYONE*/
    public static final String TEST = "/test"; /*EVERYONE*/

    //UserController ------------------------------------------------------------------------------------------

    public static final String USER_PROFILE_GET = "/user/{username}/profile"; /*EVERYONE*/
    public static final String USER_FOLLOW_POST = "/user/{username}/follow"; /*USER++*/
    public static final String USER_UNFOLLOW_POST = "/user/{username}/unfollow"; /*USER++*/

    //UserApiController ---------------------------------------------------------------------------------------

    public static final String USER_EXISTS_GET = "/api/user/exists"; /*EVERYONE*/

    //UserAuthenticationController ----------------------------------------------------------------------------

    public static final String USER_LOGIN_GET = "/login"; /*GUEST*/
    public static final String USER_REGISTER_GET = "/register"; /*GUEST*/
    public static final String USER_REGISTER_POST = "/register"; /*GUEST*/
    public static final String USER_RESET_PASSWORD_GET = "/user/reset/password"; /*EVERYONE*/
    public static final String USER_RESET_PASSWORD_POST = "/user/reset/password"; /*EVERYONE*/

    //UserAuthorizationController ----------------------------------------------------------------------------

    public static final String UNAUTHORIZED = "/unauthorized"; /*EVERYONE*/

    //UserRestController -------------------------------------------------------------------------------------

    public static final String USER_SEND_EMAIL_RESET_PASSWORD_POST = "/user/send-email/reset/password"; /*EVERYONE*/

    //PostController -----------------------------------------------------------------------------------------

    public static final String CREATE_POST_POST = "/post/{userUsername}/create"; /*USER++*/
    public static final String DELETE_POST_POST = "/post/{postId}/delete"; /*MODERATOR++ || CREATOR */

    //Thread -------------------------------------------------------------------------------------------------

    public static final String THREAD_CREATE_POST = "/thread/create"; /*ADMIN++*/
    public static final String THREAD_DELETE_POST = "/thread/{threadId}/delete"; /*ADMIN++*/
    public static final String THREAD_READ_GET = "/thread/{threadId}/read"; /*EVERYONE*/

    //ThreadRestController  ----------------------------------------------------------------------------------

    public static final String THREAD_CREATE_GET =  "/thread/create"; /*ADMIN++*/

    //ReactionController -------------------------------------------------------------------------------------

    public static final String THREAD_REACTION_LIKE_POST = "/thread/{threadId}/reaction/like"; /*USER++*/
    public static final String THREAD_REACTION_DISLIKE_POST = "/thread/{threadId}/reaction/dislike"; /*USER++*/
    public static final String COMMENT_REACTION_LIKE_POST = "/comment/{commentId}/reaction/like"; /*USER++*/
    public static final String COMMENT_REACTION_DISLIKE_POST = "/comment/{commentId}/reaction/dislike"; /*USER++*/

    //Comment ------------------------------------------------------------------------------------------------

    public static final String COMMENT_DELETE_POST = "/comment/{commentId}/delete"; /*MODERATOR++ || CREATOR */
    public static final String THREAD_CREATE_COMMENT_POST = "/thread/{threadId}/comment/create"; /*USER++*/

    //ReportController ---------------------------------------------------------------------------------------
    public static final String THREAD_REPORT_POST = "/thread/{threadId}/report"; /*USER++*/
    public static final String COMMENT_REPORT_POST = "/comment/{commentId}/report"; /*USER++*/
    public static final String POST_REPORT_POST = "/post/{postId}/report"; /*USER++*/

}

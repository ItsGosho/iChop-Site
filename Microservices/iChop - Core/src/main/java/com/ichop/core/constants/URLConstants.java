package com.ichop.core.constants;


public final class URLConstants {

    //PlayerController ---------------------------------------------------------------------------------------
    public static final String PLAYER_LINK_ACCOUNT_GET = "/player/link-account"; /*USER++*/
    public static final String PLAYER_LINK_ACCOUNT_POST = "/player/link-account"; /*USER++*/
    public static final String PLAYER_PROFILE_VIEW_GET = "/player/{uuid}"; /*EVERYONE*/

    //PlayerApiController ------------------------------------------------------------------------------------
    public static final String PLAYER_IS_PLAYER_LINKED_ACCOUNT_GET = "/api/player/is-account-linked"; /*EVERYONE*/


    //HomeController ------------------------------------------------------------------------------------------

    public static final String HOME_GET = "/"; /*EVERYONE*/
    public static final String TEST = "/test"; /*EVERYONE*/

    //UserController ------------------------------------------------------------------------------------------

    public static final String USER_PROFILE_GET = "/user/{username}/profile"; /*EVERYONE*/
    public static final String USER_ALL_GET = "/user/all"; /*ADMIN++*/

    //UserFollowController ------------------------------------------------------------------------------------

    public static final String USER_FOLLOW_POST = "/user/{username}/follow"; /*USER++*/
    public static final String USER_UNFOLLOW_POST = "/user/{username}/unfollow"; /*USER++*/
    public static final String USER_ALL_FOLLOWERS = "/api/user/{username}/all/followers"; /*EVERYONE*/
    public static final String USER_ALL_FOLLOWINGS = "/api/user/{username}/all/followings"; /*EVERYONE*/

    //UserControlController -----------------------------------------------------------------------------------

    public static final String USER_CONTROL_BASE_GET = "/user/{username}/control"; /*ADMIN++*/
    public static final String USER_CONTROL_ROLE_MANAGEMENT_GET = "/user/{username}/control/role-management"; /*ADMIN++*/
    public static final String USER_CONTROL_ROLE_MANAGEMENT_PROMOTE_USER_POST = "/user/{username}/control/role-management/promote"; /*ADMIN++*/
    public static final String USER_CONTROL_ROLE_MANAGEMENT_DEMOTE_USER_POST = "/user/{username}/control/role-management/demote"; /*ADMIN++*/


    //UserMyProfileController ---------------------------------------------------------------------------------

    public static final String USER_PROFILE_OPTIONS_INFORMATION_GET = "/user/my-profile/options/information"; /*Only LOGGED-IN USER IN HIS PROFILE*/
    public static final String USER_PROFILE_OPTIONS_INFORMATION_POST = "/user/my-profile/options/information"; /*Only LOGGED-IN USER IN HIS PROFILE*/
    public static final String USER_PROFILE_OPTIONS_CHANGE_PASSWORD_GET = "/user/my-profile/options/change-password"; /*Only LOGGED-IN USER IN HIS PROFILE*/
    public static final String USER_PROFILE_OPTIONS_CHANGE_PASSWORD_POST = "/user/my-profile/options/change-password"; /*Only LOGGED-IN USER IN HIS PROFILE*/
    public static final String USER_PROFILE_OPTIONS_MINECRAFT_GET = "/user/my-profile/options/minecraft"; /*Only LOGGED-IN USER IN HIS PROFILE*/
    public static final String USER_PROFILE_OPTIONS_MINECRAFT_UNLINK_POST = "/user/my-profile/options/minecraft/unlink"; /*Only LOGGED-IN USER IN HIS PROFILE*/

    //UserAuthenticationController ----------------------------------------------------------------------------

    public static final String USER_LOGIN_GET = "/login"; /*GUEST*/
    public static final String USER_REGISTER_GET = "/register"; /*GUEST*/
    public static final String USER_REGISTER_POST = "/register"; /*GUEST*/
    public static final String USER_RESET_PASSWORD_GET = "/user/reset/password"; /*EVERYONE*/
    public static final String USER_RESET_PASSWORD_POST = "/user/reset/password"; /*EVERYONE*/

    //UserAuthorizationController ----------------------------------------------------------------------------

    public static final String UNAUTHORIZED = "/unauthorized"; /*EVERYONE*/

    //UserRestController -------------------------------------------------------------------------------------

    public static final String USER_SEND_EMAIL_RESET_PASSWORD_POST = "/api/user/send-email/reset/password"; /*EVERYONE*/
    public static final String USER_EXISTS_GET = "/api/user/exists"; /*EVERYONE*/
    public static final String IS_USER_FOLLOWING_USER = "/api/user/is-following"; /*EVERYONE*/

    //PostController -----------------------------------------------------------------------------------------

    public static final String CREATE_POST_POST = "/post/{userUsername}/create"; /*USER++*/
    public static final String DELETE_POST_POST = "/post/{postId}/delete"; /*MODERATOR++ || CREATOR || ON_YOUR_PROFILE*/

    //Thread -------------------------------------------------------------------------------------------------

    public static final String THREAD_CREATE_GET = "/thread/create"; /*MODERATOR++*/
    public static final String THREAD_DELETE_POST = "/thread/{threadId}/delete"; /*MODERATOR++*/
    public static final String THREAD_READ_GET = "/thread/{threadId}/read"; /*EVERYONE*/

    //ThreadRestController  ----------------------------------------------------------------------------------

    public static final String THREAD_CREATE_POST = "/thread/create"; /*MODERATOR++*/

    //ReactionController -------------------------------------------------------------------------------------

    public static final String THREAD_REACTION_DISLIKE_POST = "/thread/{threadId}/reaction/{reaction}"; /*USER++*/
    public static final String COMMENT_REACTION_DISLIKE_POST = "/comment/{commentId}/reaction/{reaction}"; /*USER++*/

    //ReactionApiController
    public static final String IS_THREAD_ALREADY_REACTED_BY_USER = "/thread/{threadId}/is-reaction-present"; /*EVERYONE*/
    public static final String IS_COMMENT_ALREADY_REACTED_BY_USER = "/comment/{commentId}/is-reaction-present"; /*EVERYONE*/

    //Comment ------------------------------------------------------------------------------------------------

    public static final String COMMENT_DELETE_POST = "/comment/{commentId}/delete"; /*MODERATOR++ || CREATOR */
    public static final String THREAD_CREATE_COMMENT_POST = "/thread/{threadId}/comment/create"; /*USER++*/

    //ReportController ---------------------------------------------------------------------------------------
    public static final String THREAD_REPORT_POST = "/thread/{threadId}/report"; /*USER++*/
    public static final String THREAD_REPORT_DELETE_POST = "/thread/report/{reportId}/delete"; /*OWNER++*/
    public static final String THREAD_REPORTS_ALL_GET = "/thread/reports/all"; /*OWNER++*/

    public static final String COMMENT_REPORT_POST = "/comment/{commentId}/report"; /*USER++*/
    public static final String COMMENT_REPORT_DELETE_POST = "/comment/report/{reportId}/delete"; /*MODERATOR++*/
    public static final String COMMENT_REPORTS_ALL_GET = "/comment/reports/all"; /*MODERATOR++*/

    public static final String POST_REPORT_POST = "/post/{postId}/report"; /*MODERATOR++*/
    public static final String POST_REPORT_DELETE_POST = "/post/report/{reportId}/delete"; /*MODERATOR++*/
    public static final String POST_REPORTS_ALL_GET = "/post/reports/all"; /*MODERATOR++*/

    //ThreadReportApiController
    public static final String IS_THREAD_ALREADY_REPORTED_BY_USER = "/thread/{threadId}/is-report-present"; /*EVERYONE*/

    //CommentReportApiController
    public static final String IS_COMMENT_ALREADY_REPORTED_BY_USER = "/comment/{commentId}/is-report-present"; /*EVERYONE*/

    //PostReportApiController
    public static final String IS_POST_ALREADY_REPORTED_BY_USER = "/post/{postId}/is-report-present"; /*EVERYONE*/

}

package ichop.core.constants;


public final class URLConstants {

    //PlayerController ---------------------------------------------------------------------------------------
    public static final String PLAYER_LINK_ACCOUNT = "/player/link-account"; /*USER++*/
    public static final String PLAYER_PROFILE_VIEW = "/player/{uuid}"; /*EVERYONE*/

    //PlayerApiController ------------------------------------------------------------------------------------
    public static final String PLAYER_IS_PLAYER_LINKED_ACCOUNT = "/api/player/is-account-linked"; /*EVERYONE*/

    //PostController -----------------------------------------------------------------------------------------

    public static final String CREATE_POST = "/post/{userUsername}/create"; /*USER++*/
    public static final String DELETE_POST = "/post/{postId}/delete"; /*MODERATOR++ || CREATOR || ON_YOUR_PROFILE*/

    //Thread -------------------------------------------------------------------------------------------------

    public static final String THREAD_CREATE = "/thread/create"; /*MODERATOR++*/
    public static final String THREAD_DELETE = "/thread/{threadId}/delete"; /*MODERATOR++*/
    public static final String THREAD_READ = "/thread/{threadId}/read"; /*EVERYONE*/

    //ReactionController -------------------------------------------------------------------------------------

    public static final String THREAD_REACTION_DISLIKE = "/thread/{threadId}/reaction/{reaction}"; /*USER++*/
    public static final String COMMENT_REACTION_DISLIKE = "/comment/{commentId}/reaction/{reaction}"; /*USER++*/

    //ReactionApiController
    public static final String IS_THREAD_ALREADY_REACTED_BY_USER = "/thread/{threadId}/is-reaction-present"; /*EVERYONE*/
    public static final String IS_COMMENT_ALREADY_REACTED_BY_USER = "/comment/{commentId}/is-reaction-present"; /*EVERYONE*/

    //Comment ------------------------------------------------------------------------------------------------

    public static final String COMMENT_DELETE = "/comment/{commentId}/delete"; /*MODERATOR++ || CREATOR */
    public static final String THREAD_CREATE_COMMENT = "/thread/{threadId}/comment/create"; /*USER++*/

    //ReportController ---------------------------------------------------------------------------------------
    public static final String THREAD_REPORT = "/thread/{threadId}/report"; /*USER++*/
    public static final String THREAD_REPORT_DELETE = "/thread/report/{reportId}/delete"; /*OWNER++*/
    public static final String THREAD_REPORTS_ALL = "/thread/reports/all"; /*OWNER++*/

    public static final String COMMENT_REPORT = "/comment/{commentId}/report"; /*USER++*/
    public static final String COMMENT_REPORT_DELETE = "/comment/report/{reportId}/delete"; /*MODERATOR++*/
    public static final String COMMENT_REPORTS_ALL = "/comment/reports/all"; /*MODERATOR++*/

    public static final String POST_REPORT = "/post/{postId}/report"; /*MODERATOR++*/
    public static final String POST_REPORT_DELETE = "/post/report/{reportId}/delete"; /*MODERATOR++*/
    public static final String POST_REPORTS_ALL = "/post/reports/all"; /*MODERATOR++*/

    //ThreadReportApiController
    public static final String IS_THREAD_ALREADY_REPORTED_BY_USER = "/thread/{threadId}/is-report-present"; /*EVERYONE*/

    //CommentReportApiController
    public static final String IS_COMMENT_ALREADY_REPORTED_BY_USER = "/comment/{commentId}/is-report-present"; /*EVERYONE*/

    //PostReportApiController
    public static final String IS_POST_ALREADY_REPORTED_BY_USER = "/post/{postId}/is-report-present"; /*EVERYONE*/

}

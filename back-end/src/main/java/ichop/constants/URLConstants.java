package ichop.constants;


public final class URLConstants {

    //HomeController
    public static final String HOME_GET = "/";
    public static final String TEST = "/test";


    //User ----------------------------------------------------------------------------------------------------

    //UserController ------------------------------------------------------------------------------------------
    public static final String USER_PROFILE_GET = "/user/{username}/profile";
    public static final String USER_FOLLOW_POST = "/user/{username}/follow";

    //UserApiController ---------------------------------------------------------------------------------------
    public static final String USER_EXISTS_GET = "/api/user/exists";

    //UserAuthenticationController ----------------------------------------------------------------------------
    public static final String USER_LOGIN_GET = "/login";
    public static final String USER_REGISTER_GET = "/register";
    public static final String USER_REGISTER_POST = "/register";
    public static final String USER_RESET_PASSWORD_GET = "/user/reset/password";
    public static final String USER_RESET_PASSWORD_POST = "/user/reset/password";

    //UserAuthorizationController ----------------------------------------------------------------------------
    public static final String UNAUTHORIZED = "/unauthorized";

    //UserRestController -------------------------------------------------------------------------------------
    public static final String USER_SEND_EMAIL_RESET_PASSWORD_POST = "/user/send-email/reset/password";


    //PostController -----------------------------------------------------------------------------------------
    public static final String CREATE_POST_POST = "/post/{userUsername}/create";
    public static final String DELETE_POST_POST = "/post/{postId}/delete";


    //Thread -------------------------------------------------------------------------------------------------

    public static final String THREAD_CREATE_POST = "/thread/create";
    public static final String THREAD_DELETE_POST = "/thread/{threadId}/delete";
    public static final String THREAD_READ_GET = "/thread/{threadId}/read";

    //ThreadRestController  ----------------------------------------------------------------------------------
    public static final String THREAD_CREATE_GET =  "/thread/create";


    //ReactionController -------------------------------------------------------------------------------------

    public static final String THREAD_REACTION_LIKE_POST = "/thread/{threadId}/reaction/like";
    public static final String THREAD_REACTION_DISLIKE_POST = "/thread/{threadId}/reaction/dislike";
    public static final String COMMENT_REACTION_LIKE_POST = "/comment/{commentId}/reaction/like";
    public static final String COMMENT_REACTION_DISLIKE_POST = "/comment/{commentId}/reaction/dislike";

    //Comment ------------------------------------------------------------------------------------------------

    public static final String COMMENT_DELETE_POST = "/comment/{commentId}/delete";
    public static final String THREAD_CREATE_COMMENT_POST = "/thread/{threadId}/comment/create";

    //ReportController ---------------------------------------------------------------------------------------
    public static final String THREAD_REPORT_POST = "/thread/{threadId}/report";
    public static final String COMMENT_REPORT_POST = "/comment/{commentId}/report";

}

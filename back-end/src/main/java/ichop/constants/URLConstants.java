package ichop.constants;


public final class URLConstants {

    //HomeController
    public static final String HOME_GET = "/";
    public static final String TEST = "/test";


    //User ---------------------------------------------------------------------------------------------------

    //-UserController
    public static final String USER_PROFILE_GET = "/user/{username}/profile";

    //-UserPostsController
    public static final String USER_CREATE_POST_POST = "/post/{userId}/create";

    //-UserApiController
    public static final String USER_EXISTS_GET = "/api/user/exists";

    //-UserAuthenticationController
    public static final String USER_LOGIN_GET = "/login";
    public static final String USER_REGISTER_GET = "/register";
    public static final String USER_REGISTER_POST = "/register";
    public static final String USER_RESET_PASSWORD_GET = "/user/reset/password";
    public static final String USER_RESET_PASSWORD_POST = "/user/reset/password";

    //-UserAuthorizationController
    public static final String UNAUTHORIZED = "/unauthorized";

    //-UserRestController
    public static final String USER_SEND_EMAIL_RESET_PASSWORD_POST = "/user/send-email/reset/password";


    //Thread -------------------------------------------------------------------------------------------------

    //-ThreadController
    public static final String THREAD_CREATE_POST = "/thread/create";
    public static final String THREAD_DELETE_POST = "/thread/{id}/delete";
    public static final String THREAD_READ_GET = "/thread/{id}/read";

    //-ThreadRestController
    public static final String THREAD_CREATE_GET =  "/thread/create";

    //-CommentController
    public static final String COMMENT_DELETE_POST = "/comment/{id}/delete";
    public static final String THREAD_CREATE_COMMENT_POST = "/thread/{id}/comment/create";

    //-ReactController
    public static final String THREAD_REACTION_LIKE_POST = "/thread/{id}/reaction/like";
    public static final String THREAD_REACTION_DISLIKE_POST = "/thread/{id}/reaction/dislike";
    public static final String COMMENT_REACTION_LIKE_POST = "/comment/{id}/reaction/like";
    public static final String COMMENT_REACTION_DISLIKE_POST = "/comment/{id}/reaction/dislike";

    //-ReportController
    public static final String THREAD_REPORT_POST = "/thread/{id}/report";
    public static final String COMMENT_REPORT_POST = "/comment/{id}/report";

}

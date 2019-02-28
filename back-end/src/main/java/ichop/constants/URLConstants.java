package ichop.constants;

public final class URLConstants {

    //HomeController
    public static final String HOME_GET = "/";
    public static final String TEST = "/test";


    //User ---------------------------------------------------------------------------------------------------

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
    public static final String THREAD_DELETE_GET = "/thread/{id}/delete";
    public static final String THREAD_READ_GET = "/thread/{id}/read";

    //-CommentController
    public static final String COMMENT_DELETE = "/comment/{id}/delete";
    public static final String COMMENT_THREAD_POST = "/thread/{id}/comment/create";

    //-ReactController
    public static final String THREAD_REACTION = "/thread/{id}/reaction/{reactionType}";
    public static final String COMMENT_REACTION = "/comment/{id}/reaction/{reactionType}";

    //-ThreadRestController
    public static final String THREAD_CREATE_GET =  "/thread/create";

}

package ichop.constants;

public final class URLConstants {

    //HomeController
    public static final String HOME_GET = "/";
    public static final String TEST = "/test";


    //User ---------------------------------------------------------------------------------------------------

    //-UserApiController
    private static final String USER_API_PREFIX = "/api/users";
    public static final String USER_EXISTS_GET = USER_API_PREFIX + "/exists";

    //-UserAuthenticationController
    public static final String USER_LOGIN_GET = "/login";
    public static final String USER_REGISTER_GET = "/register";
    public static final String USER_REGISTER_POST = "/register";
    public static final String USER_RESET_PASSWORD_GET = "/users/reset/password";
    public static final String USER_RESET_PASSWORD_POST = "/users/reset/password";

    //-UserAuthorizationController
    public static final String UNAUTHORIZED = "/unauthorized";

    //-UserRestController
    public static final String USER_SEND_EMAIL_RESET_PASSWORD_POST = "/users/send-email/reset/password";

    //Thread -------------------------------------------------------------------------------------------------

    //-ThreadController
    private static final String THREAD_PREFIX = "/thread";
    public static final String THREAD_CREATE_POST = THREAD_PREFIX + "/create";
    public static final String THREAD_DELETE_GET = THREAD_PREFIX + "/{id}/delete";

    //-ThreadRestController
    public static final String THREAD_CREATE_GET = THREAD_PREFIX + "/create";

}

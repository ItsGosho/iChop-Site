package itsgosho.constants;

public final class URLConstants {

    //TODO: da go izmislq po dobre ,da nqma request mapping a da se slaga tuk prefixa
    //TODO: + da ima _GET/POST za da se znae koe za kakvo e!

    //HomeController
    public static final String HOME = "";
    public static final String TEST = "/test";


    //User ---------------------------------------------------------------------------------------------------

    //-UserApiController
    public static final String USER_API_PREFIX = "/api/users";
    public static final String USER_EXISTS = "/exists";

    //-UserAuthenticationController
    public static final String USER_LOGIN = "/login";
    public static final String USER_REGISTER = "/register";
    public static final String USER_RESET_PASSWORD = "/users/reset/password";

    //-UserAuthorizationController
    public static final String UNAUTHORIZED = "/unauthorized";

    //-UserRestController
    public static final String USER_SEND_EMAIL_RESET_PASSWORD = "/users/send-email/reset/password";

    //Thread -------------------------------------------------------------------------------------------------

    //-ThreadController
    public static final String THREAD_PREFIX = "/thread";
    public static final String THREAD_CREATE = "/create";
    public static final String THREAD_DELETE = "/{id}/delete";
}

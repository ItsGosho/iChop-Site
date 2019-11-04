package ichop.core.areas.comment.constants;

public final class CommentRoutingConstants {

    public static final String THREAD_CREATE = "/thread/{id}/comment/create";
    public static final String THREAD_DELETE = "/thread/{id}/comment/{id}/delete";

    public static final String USER_PROFILE_CREATE = "/user/{username}/comment/create";
    public static final String USER_PROFILE_DELETE = "/user/{username}/comment/{id}/delete";

    public static final String THREAD_ALL = "/thread/{id}/comment/all";
    public static final String USER_PROFILE_ALL = "/user/{username}/comment/all";
}

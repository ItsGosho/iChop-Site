package ichop.core.areas.comment.constants;

public final class CommentRoutingConstants {

    public static final String THREAD_CREATE = "/thread/{threadId}/comment/create";
    public static final String THREAD_DELETE = "/thread/{threadId}/comment/{commentId}/delete";

    public static final String USER_PROFILE_CREATE = "/user/{userProfileUsername}/comment/create";
    public static final String USER_PROFILE_DELETE = "/user/{userProfileUsername}/comment/{commentId}/delete";

    public static final String THREAD_ALL = "/thread/{threadId}/comment/all";
    public static final String USER_PROFILE_ALL = "/user/{userProfileUsername}/comment/all";
}

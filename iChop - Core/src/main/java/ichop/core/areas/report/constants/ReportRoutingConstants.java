package ichop.core.areas.report.constants;

public final class ReportRoutingConstants {

    public static final String THREAD_CREATE = "/report/thread/{threadId}/create";
    public static final String THREAD_COMMENT_CREATE = "/report/thread/{threadId}/comment/{commentId}/create";

    public static final String THREAD_REMOVE = "/report/thread/{threadId}/remove";
    public static final String THREAD_COMMENT_REMOVE = "/report/thread/{threadId}/comment/{commentId}/remove";
}

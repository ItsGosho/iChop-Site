package ichop.threads.constants;

import static ichop.threads.constants.ColorCodesConstants.*;

public final class JmsLoggingConstants {

    public static final String SEND_AND_RECEIVED_STARTED = ANSI_GREEN + "Send and receive has started for destination -> [%s]";
    public static final String SEND_STARTED = ANSI_GREEN + "Send has started for destination -> [%s]";
    public static final String REPLY_TO_STARTED = ANSI_GREEN + "Reply to has started for destination -> [%s]";
    public static final String VALIDATION_ERROR_REPLY_WILL_START = ANSI_RED + "Reply with validation error will start for-> [%s]";

}

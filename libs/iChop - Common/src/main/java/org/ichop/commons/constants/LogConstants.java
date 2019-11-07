package org.ichop.commons.constants;


import static org.ichop.commons.constants.ColorCodesConstants.*;

public final class LogConstants {

    private static final String SERVICE_PREFIX = "[iChop - Commons] ";

    public static final String SEND_AND_RECEIVED_STARTED = wrap("Send and receive has started for destination -> [%s]");
    public static final String SEND_STARTED = wrap("Send has started for destination -> [%s]");
    public static final String REPLY_TO_STARTED = wrap("Reply to has started for destination -> [%s]");
    public static final String VALIDATION_ERROR_REPLY_WILL_START = wrap("Reply with validation error [%s] will start for-> [%s]");
    public static final String ERROR_REPLY_WILL_START = wrap("Reply with error [%s] will start for-> [%s]");

    private static String wrap(String str) {
        return ANSI_PURPLE + SERVICE_PREFIX + ANSI_GREEN + str + ANSI_BLACK;
    }
}

package ichop.core.areas.user.constants;

import static ichop.core.common.constants.ColorCodesConstants.*;

public final class UserLogConstants {

    public static final String REGISTRATION_SUCCESSFUL = wrap("Registration was successful for email -> %s");


    private static String wrap(String msg) {
        return ANSI_BLUE + "[User] " + ANSI_GREEN + msg + ANSI_BLACK;
    }


}

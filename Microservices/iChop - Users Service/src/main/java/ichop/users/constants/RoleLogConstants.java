package ichop.users.constants;

import static org.ichop.commons.constants.ColorCodesConstants.*;

public final class RoleLogConstants {

    public static final String ROLE_CREATED = wrap("Role has been created with name -> %s");


    private static String wrap(String msg) {
        return ANSI_BLUE + "[Role] " + ANSI_GREEN + msg + ANSI_BLACK;
    }
}

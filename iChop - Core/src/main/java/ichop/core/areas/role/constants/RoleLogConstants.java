package ichop.core.areas.role.constants;

import static ichop.core.common.constants.ColorCodesConstants.*;

public final class RoleLogConstants {

    public static final String ROLE_CREATED = wrap("Role has been created with name -> %s");


    private static String wrap(String msg) {
        return ANSI_BLUE + "[Role] " + ANSI_GREEN + msg + ANSI_BLACK;
    }
}

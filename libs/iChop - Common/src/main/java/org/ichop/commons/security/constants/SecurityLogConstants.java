package org.ichop.commons.security.constants;

import static org.ichop.commons.constants.ColorCodesConstants.*;

public final class SecurityLogConstants {

    public static final String AUTHENTICATION_STARTED = wrap("Authentication started for email %s");
    public static final String AUTHENTICATION_SUCCESSFUL = wrap("Authentication was successful for email %s");
    public static final String JWT_GENERATION_SUCCESSFUL = wrap("JWT was generated successful for email %s !");



    private static String wrap(String msg) {
        return ANSI_BLUE + "[Security] " + ANSI_GREEN + msg + ANSI_BLACK;
    }
}

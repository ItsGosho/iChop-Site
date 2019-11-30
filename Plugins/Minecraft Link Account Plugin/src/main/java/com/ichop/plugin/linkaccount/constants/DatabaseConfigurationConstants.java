package com.ichop.plugin.linkaccount.constants;

public final class DatabaseConfigurationConstants {

    public static final String USER = "root";
    public static final String PASSWORD = "1234";

    public static final String DOMAIN = "localhost";
    public static final String PORT = "3306";
    public static final String NAME = "ichop_link_account_plugin";

    public static final String CONNECTION_URL = URL(DOMAIN, PORT, NAME);

    private static String URL(String domain, String port, String name) {
        return "jdbc:mysql://" + domain + ":" + port + "/" + name + "?useSSL=false&amp;createDatabaseIfNotExist=true&amp;serverTimezone=UTC";
    }
}

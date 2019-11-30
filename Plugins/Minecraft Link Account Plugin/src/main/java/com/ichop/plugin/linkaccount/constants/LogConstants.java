package com.ichop.plugin.linkaccount.constants;

public final class LogConstants {

    private static final String PREFIX = "[Link Account] -> ";

    public static final String ACTIVEMQ_INITIALIZED = PREFIX + "ActiveMQ has been initialized for [Broker URL: %s;Username: %s]";
    public static final String ACTIVEMQ_LISTENER_INITIALIZED = PREFIX + "Destination [%s] has been initialized and bind to the provided listener!";
    public static final String DATABASE_ENTITY_MANAGER_INITIALIZED = PREFIX + "The EntityManger has been initialized for [DB: %s;USER: %s;]";

}

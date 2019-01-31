package itsgosho.constants;

import org.springframework.beans.factory.annotation.Value;

//these values come from the application.properties
public final class ServerConstants {

    @Value("${custom.server.protocol}")
    public static String SERVER_PROTOCOL;

    @Value("${custom.server.domain}")
    public static String SEVER_DOMAIN;

    @Value("${server.port}")
    public static String SERVER_PORT;
}

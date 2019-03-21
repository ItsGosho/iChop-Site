package com.ichop.core.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//these values come from the application.properties
@Component
public final class ServerConstants {

    public static String SERVER_PROTOCOL;
    public static String SEVER_DOMAIN;
    public static String SERVER_PORT;

    @Value("${custom.server.protocol}")
    private void setServerProtocol(String serverProtocol){
        SERVER_PROTOCOL = serverProtocol;
    }

    @Value("${custom.server.domain}")
    private void setSeverDomain(String severDomain) {
        SEVER_DOMAIN = severDomain;
    }

    @Value("${server.port}")
    private void setServerPort(String serverPort) {
        SERVER_PORT = serverPort;
    }
}

package ichop.user.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class ServerConstants {

    public static String SERVER_PROTOCOL;
    public static String SEVER_DOMAIN;
    public static String SERVER_PORT;
    public static String SERVER_URL;

    @Value("${server.protocol}")
    private void setServerProtocol(String serverProtocol){
        SERVER_PROTOCOL = serverProtocol;
    }

    @Value("${server.domain}")
    private void setSeverDomain(String severDomain) {
        SEVER_DOMAIN = severDomain;
    }

    @Value("${server.port}")
    private void setServerPort(String serverPort) {
        SERVER_PORT = serverPort;
    }

    @Value("${server.url}")
    private void setServerUrl(String serverUrl) {
        SERVER_URL = serverUrl;
    }
}

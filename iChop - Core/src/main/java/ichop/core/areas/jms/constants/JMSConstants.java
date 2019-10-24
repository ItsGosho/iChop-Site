package ichop.core.areas.jms.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class JMSConstants {

    public static String SEND_MODEL_PARAMETER_NAME;
    public static String RECEIVE_MODEL_PARAMETER_NAME;

    @Value("${jms.send.parameter.name}")
    public void setSendModelParameterName(String sendModelParameterName) {
        SEND_MODEL_PARAMETER_NAME = sendModelParameterName;
    }

    @Value("${jms.receive.parameter.name}")
    public void setReceiveModelParameterName(String receiveModelParameterName) {
        RECEIVE_MODEL_PARAMETER_NAME = receiveModelParameterName;
    }
}

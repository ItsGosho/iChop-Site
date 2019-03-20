package ichop.components.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.HashMap;

@Service
public class JmsServicesImp implements JmsServices {

    public static final String UPDATE_AVATAR_DESTINATION = "set_user_avatar";
    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsServicesImp(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    @Override
    public void sendUpdateAvatarRequest(String username, String imageAsBase64String) {
        HashMap<String,Object> values = new HashMap<>();
        values.put("username",username);
        values.put("avatar",imageAsBase64String);

        ObjectMessage objectMessage = this.fillObjectMessage(values);

        this.jmsTemplate.convertAndSend(UPDATE_AVATAR_DESTINATION,objectMessage);

    }

    private ObjectMessage fillObjectMessage(HashMap<String,Object> values) {
        Session session = null;
        try {
            session = this.jmsTemplate.getConnectionFactory().createConnection().createSession(false,Session.AUTO_ACKNOWLEDGE);
            ObjectMessage objectMessage = session.createObjectMessage();
            objectMessage.setObject(values);
            return objectMessage;
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return null;
    }

}

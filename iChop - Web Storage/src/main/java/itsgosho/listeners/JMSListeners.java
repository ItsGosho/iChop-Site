package itsgosho.listeners;

import itsgosho.services.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

@Component
public class JMSListeners {

    private final UserServices userServices;

    @Autowired
    public JMSListeners(UserServices userServices) {
        this.userServices = userServices;
    }


    @JmsListener(destination = "set-user-avatar")
    public void setUserAvatar(ObjectMessage objectMessage) throws IOException {
        HashMap<String,Object> messages = this.objectMessageToMessages(objectMessage);

        String username = (String) messages.get("userUsername");
        String avatarBinary = (String) messages.get("avatarAsBinary64");

        byte[] imageBytes = DatatypeConverter.parseBase64Binary(avatarBinary);
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

        this.userServices.updateAvatar(username,bufferedImage);

    }

    @JmsListener(destination = "get-user-avatar")
    public void recieveUserAvatar(ObjectMessage objectMessage) throws JMSException {
        HashMap<String,Object> messages = this.objectMessageToMessages(objectMessage);

    }

    @SuppressWarnings("unchecked")
    private HashMap<String,Object> objectMessageToMessages(ObjectMessage objectMessage){
        Object object = null;
        try {
            object = objectMessage.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return (HashMap<String, Object>) object;
    }

}

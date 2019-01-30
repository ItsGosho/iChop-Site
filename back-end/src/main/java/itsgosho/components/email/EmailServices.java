package itsgosho.components.email;

import javax.mail.MessagingException;
import java.util.Map;

public interface EmailServices {

    void sendResetPasswordEmail(String userEmail, String token);
}

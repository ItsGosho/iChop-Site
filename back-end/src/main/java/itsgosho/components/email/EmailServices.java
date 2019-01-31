package itsgosho.components.email;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Map;

public interface EmailServices {

    void sendResetPasswordEmail(String userEmail, String token, LocalDateTime tokenExpireDate);
}

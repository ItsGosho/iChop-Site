package ichop.components.email;

import java.time.LocalDateTime;

public interface EmailServices {

    void sendResetPasswordEmail(String userEmail, String token, LocalDateTime tokenExpireDate);
}

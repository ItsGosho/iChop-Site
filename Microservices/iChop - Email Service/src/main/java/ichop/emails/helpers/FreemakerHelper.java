package ichop.emails.helpers;

import java.time.LocalDateTime;

public interface FreemakerHelper {


    String proceed(String templatePath, Object object) throws Exception;

    String prepareResetPasswordView(LocalDateTime expirationDate, String token) throws Exception;
}

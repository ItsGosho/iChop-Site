package ichop.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Sorry ,but the password and confirm password doesnt match!")
public class PasswordsDoesntMatchException extends RuntimeException {
}

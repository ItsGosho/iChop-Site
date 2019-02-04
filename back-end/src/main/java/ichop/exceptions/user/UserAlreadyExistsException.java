package ichop.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Sorry ,but the user already exists!")
public class UserAlreadyExistsException extends RuntimeException {
}


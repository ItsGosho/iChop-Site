package ichop.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Sorry ,but the user wasn't found!")
public class UserNotFoundException extends RuntimeException {
}

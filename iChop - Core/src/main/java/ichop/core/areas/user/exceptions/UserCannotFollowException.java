package ichop.core.areas.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserCannotFollowException extends RuntimeException {

    public UserCannotFollowException() {
    }

    public UserCannotFollowException(String message) {
        super(message);
    }
}

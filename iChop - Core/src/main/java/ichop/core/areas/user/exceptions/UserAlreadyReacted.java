package ichop.core.areas.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyReacted extends RuntimeException {

    public UserAlreadyReacted() {
    }

    public UserAlreadyReacted(String message) {
        super(message);
    }
}

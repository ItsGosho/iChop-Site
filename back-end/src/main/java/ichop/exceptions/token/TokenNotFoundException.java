package ichop.exceptions.token;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Sorry ,but the token wasn't found!")
public class TokenNotFoundException extends RuntimeException {
}

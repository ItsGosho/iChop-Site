package ichop.core.areas.token.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException() {
    }

    public TokenNotFoundException(String message) {
        super(message);
    }
}

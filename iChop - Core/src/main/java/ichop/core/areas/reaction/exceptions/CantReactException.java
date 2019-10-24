package ichop.core.areas.reaction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CantReactException extends RuntimeException {

    public CantReactException() {
    }

    public CantReactException(String message) {
        super(message);
    }


}

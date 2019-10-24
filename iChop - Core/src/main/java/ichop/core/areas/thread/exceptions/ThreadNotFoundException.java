package ichop.core.areas.thread.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ThreadNotFoundException extends RuntimeException {

    public ThreadNotFoundException() {
    }

    public ThreadNotFoundException(String message) {
        super(message);
    }
}

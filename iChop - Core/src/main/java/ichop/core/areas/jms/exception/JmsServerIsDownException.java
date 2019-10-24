package ichop.core.areas.jms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class JmsServerIsDownException extends RuntimeException {

    public JmsServerIsDownException() {
    }

    public JmsServerIsDownException(String message) {
        super(message);
    }
}

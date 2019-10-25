package ichop.core.helpers;

import org.springframework.http.ResponseEntity;

public interface ResponseHelpers {
    ResponseEntity respondError(String error);

    ResponseEntity respondSuccessful(String message);

    ResponseEntity respondSuccessful(String message, Object data);
}

package ichop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Sorry ,but the entered data was invalid")
public class GlobalBindingException extends RuntimeException {
}

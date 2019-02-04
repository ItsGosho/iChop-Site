package ichop.exceptions.thread;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Sorry ,but the provided thread cannot be null!")
public class ThreadCannotBeNullException extends RuntimeException {

}

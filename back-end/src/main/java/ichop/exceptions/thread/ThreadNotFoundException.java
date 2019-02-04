package ichop.exceptions.thread;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Sorry ,but this thread wasn't found!")
public class ThreadNotFoundException extends RuntimeException {
}

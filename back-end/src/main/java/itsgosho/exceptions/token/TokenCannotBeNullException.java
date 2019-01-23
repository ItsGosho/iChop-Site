package itsgosho.exceptions.token;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Sorry ,but the provided token cannot be null!")
public class TokenCannotBeNullException extends RuntimeException {
}

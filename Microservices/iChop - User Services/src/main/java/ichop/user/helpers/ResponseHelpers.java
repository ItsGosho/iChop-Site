package ichop.user.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

public interface ResponseHelpers {


    ResponseEntity respondError(String error);

    String respondErrorJson(String error) throws JsonProcessingException;

    ResponseEntity respondSuccessful(String message);

    String respondSuccessfulJson(String message) throws JsonProcessingException;

    void respondSuccessful(HttpServletResponse httpServletResponse, String message);

    void respondError(HttpServletResponse httpServletResponse, String error);

    ResponseEntity respondSuccessful(String message, Object data);
}

package ichop.core.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.models.ResponseError;
import ichop.core.models.ResponseSuccessful;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class ResponseHelpersImp implements ResponseHelpers {

    private final ObjectMapper objectMapper;

    @Autowired
    public ResponseHelpersImp(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity respondError(String error) {
        ResponseError response = new ResponseError(error);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String respondErrorJson(String error) throws JsonProcessingException {
        ResponseError response = new ResponseError(error);

        return this.objectMapper.writeValueAsString(response);
    }

    @Override
    public ResponseEntity respondSuccessful(String message) {
        ResponseSuccessful response = new ResponseSuccessful(message);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public String respondSuccessfulJson(String message) throws JsonProcessingException {
        ResponseSuccessful response = new ResponseSuccessful(message);

        return this.objectMapper.writeValueAsString(response);
    }

    @Override
    public void respondSuccessful(HttpServletResponse httpServletResponse, String message) {
        ResponseSuccessful response = new ResponseSuccessful(message);

        this.writeToResponse(httpServletResponse, response);
    }

    @Override
    public void respondError(HttpServletResponse httpServletResponse, String error) {
        ResponseError response = new ResponseError(error);

        this.writeToResponse(httpServletResponse, response);
    }

    @Override
    public ResponseEntity respondSuccessful(String message, Object data) {
        ResponseSuccessful response = new ResponseSuccessful(message, data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void writeToResponse(HttpServletResponse response, Object object) {
        try {
            response.setContentType("application/json");
            response.getWriter().write(this.objectMapper.writeValueAsString(object));
            response.getWriter().close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

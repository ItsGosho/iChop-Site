package ichop.core.helpers;

import ichop.core.models.ResponseError;
import ichop.core.models.ResponseSuccessful;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHelpersImp implements ResponseHelpers {


    @Override
    public ResponseEntity respondError(String error) {
        ResponseError response = new ResponseError();
        response.setError(error);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity respondSuccessful(String message) {
        ResponseSuccessful response = new ResponseSuccessful();
        response.setMessage(message);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity respondSuccessful(String message, Object data) {
        ResponseSuccessful response = new ResponseSuccessful();
        response.setMessage(message);
        response.setData(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

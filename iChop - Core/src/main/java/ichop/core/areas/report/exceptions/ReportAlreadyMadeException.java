package ichop.core.areas.report.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ReportAlreadyMadeException extends RuntimeException {

    public ReportAlreadyMadeException() {
    }

    public ReportAlreadyMadeException(String message) {
        super(message);
    }
}

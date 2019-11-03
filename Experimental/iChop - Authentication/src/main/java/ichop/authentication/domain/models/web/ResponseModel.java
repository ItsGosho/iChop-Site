package ichop.authentication.domain.models.web;

public abstract class ResponseModel {

    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

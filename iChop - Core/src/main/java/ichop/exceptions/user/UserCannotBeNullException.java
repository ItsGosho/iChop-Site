package ichop.exceptions.user;

public class UserCannotBeNullException extends RuntimeException {

    public UserCannotBeNullException() {
    }

    public UserCannotBeNullException(String message) {
        super(message);
    }
}

package ichop.exceptions.user;

public class UserCannotFollowException extends RuntimeException {

    public UserCannotFollowException() {
    }

    public UserCannotFollowException(String message) {
        super(message);
    }
}

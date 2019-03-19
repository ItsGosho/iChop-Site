package ichop.exceptions.user;

public class UserNotFollowingHimException extends RuntimeException {

    public UserNotFollowingHimException() {
    }

    public UserNotFollowingHimException(String message) {
        super(message);
    }
}

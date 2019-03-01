package ichop.exceptions.user;

public class UserAlreadyReacted extends RuntimeException {

    public UserAlreadyReacted() {
    }

    public UserAlreadyReacted(String message) {
        super(message);
    }
}

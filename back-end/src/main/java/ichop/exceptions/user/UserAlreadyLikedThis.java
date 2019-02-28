package ichop.exceptions.user;

public class UserAlreadyLikedThis extends RuntimeException {

    public UserAlreadyLikedThis() {
    }

    public UserAlreadyLikedThis(String message) {
        super(message);
    }
}

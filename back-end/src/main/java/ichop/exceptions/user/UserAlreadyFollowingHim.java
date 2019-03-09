package ichop.exceptions.user;

public class UserAlreadyFollowingHim extends RuntimeException {

    public UserAlreadyFollowingHim() {
    }

    public UserAlreadyFollowingHim(String message) {
        super(message);
    }
}

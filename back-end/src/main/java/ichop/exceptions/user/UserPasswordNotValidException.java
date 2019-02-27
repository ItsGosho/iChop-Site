package ichop.exceptions.user;

public class UserPasswordNotValidException extends RuntimeException {

    public UserPasswordNotValidException() {
    }

    public UserPasswordNotValidException(String message) {
        super(message);
    }
}

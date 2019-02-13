package ichop.exceptions.user;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }

    public UserException(UserExceptionMessages userExceptionMessages) {
        super(userExceptionMessages.getDescription());
    }
}

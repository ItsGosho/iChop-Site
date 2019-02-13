package ichop.exceptions.user;

public enum UserExceptionMessages {

    PASSWORDS_DOESNT_MATCH("The provided passwords doesn't match"),
    USERNAME_ALREADY_EXISTS("The provided username already exists."),
    EMAIL_ALREADY_EXISTS("The email already exists."),
    USER_WITH_THAT_USERNAME_NOT_FOUND("User with the provided username wasn't found"),
    USER_WITH_THAT_EMAIL_NOT_FOUND("User with the provided email wasn't found"),
    NULL("User cannot be null"),
    NOT_AUTHORIZED("No permission to access this content!"),
    DATA_INVALID("The provided data is not valid.");

    private String description;

    UserExceptionMessages(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

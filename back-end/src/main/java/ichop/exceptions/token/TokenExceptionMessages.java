package ichop.exceptions.token;

public enum  TokenExceptionMessages {

    TOKEN_IS_NULL("The provided token was null."),
    TOKEN_IS_NOT_VALID("The provided token was not valid.");

    private String description;

    TokenExceptionMessages(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

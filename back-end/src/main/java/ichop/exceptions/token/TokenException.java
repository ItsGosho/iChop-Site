package ichop.exceptions.token;

public class TokenException extends RuntimeException {

    public TokenException(String message) {
        super(message);
    }

    public TokenException(TokenExceptionMessages tokenExceptionMessages){
        super(tokenExceptionMessages.getDescription());
    }
}

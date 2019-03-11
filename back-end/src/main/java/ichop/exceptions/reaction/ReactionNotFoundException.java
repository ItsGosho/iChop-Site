package ichop.exceptions.reaction;

public class ReactionNotFoundException extends RuntimeException {

    public ReactionNotFoundException() {
    }

    public ReactionNotFoundException(String message) {
        super(message);
    }
}

package ichop.exceptions.thread;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException() {
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}

package ichop.exceptions.thread;

public class ThreadNotFoundException extends RuntimeException {

    public ThreadNotFoundException() {
    }

    public ThreadNotFoundException(String message) {
        super(message);
    }
}

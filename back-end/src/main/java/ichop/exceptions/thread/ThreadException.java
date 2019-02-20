package ichop.exceptions.thread;

import ichop.exceptions.token.TokenExceptionMessages;

public class ThreadException extends RuntimeException {

    public ThreadException(String message) {
        super(message);
    }

    public ThreadException(ThreadExceptionMessages threadExceptionMessage) {
        super(threadExceptionMessage.getDescription());
    }
}

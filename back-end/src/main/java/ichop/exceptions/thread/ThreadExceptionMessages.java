package ichop.exceptions.thread;

public enum  ThreadExceptionMessages {

    THREAD_NOT_FOUND("Thread not found.");

    private String description;

    ThreadExceptionMessages(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

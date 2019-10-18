package ichop.threads.helpers;

public interface ValidationHelper {
    boolean isValid(Object object);

    String getValidationError(Object object);
}

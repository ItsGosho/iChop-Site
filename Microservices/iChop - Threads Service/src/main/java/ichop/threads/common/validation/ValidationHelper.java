package ichop.threads.common.validation;

public interface ValidationHelper {
    boolean isValid(Object object);

    String getValidationError(Object object);
}

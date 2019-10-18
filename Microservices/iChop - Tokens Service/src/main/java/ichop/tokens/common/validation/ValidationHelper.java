package ichop.tokens.common.validation;

public interface ValidationHelper {
    boolean isValid(Object object);

    String getValidationError(Object object);
}

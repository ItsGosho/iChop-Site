package org.ichop.commons.validation;

public interface ValidationHelper {
    boolean isValid(Object object);

    String getValidationError(Object object);
}

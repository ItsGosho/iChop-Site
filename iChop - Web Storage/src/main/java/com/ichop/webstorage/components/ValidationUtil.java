package com.ichop.webstorage.components;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;

public interface ValidationUtil {
    Errors validate(Object object);

    List<String> getAllErrors(List<ObjectError> errors);
}

package com.ichop.basicstatistics.components;

import com.ichop.basicstatistics.domain.models.jms.receive.GetPlayerBasicStatisticsByUUIDJmsReceiveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidationUtilImp implements ValidationUtil {

    private final Validator validator;

    @Autowired
    public ValidationUtilImp(@Qualifier("defaultValidator") Validator validator) {
        this.validator = validator;
    }

    @Override
    public Errors validate(Object object) {
        Errors errors = new BeanPropertyBindingResult(object, object.getClass().getName());

        this.validator.validate(object, errors);

        return errors;
    }

    @Override
    public List<String> getAllErrors(List<ObjectError> errors){
        return errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
    }

}

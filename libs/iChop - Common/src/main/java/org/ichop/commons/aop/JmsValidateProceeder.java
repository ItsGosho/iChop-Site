package org.ichop.commons.aop;

import org.ichop.commons.helpers.JmsHelper;
import org.ichop.commons.validation.ValidationHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.lang.reflect.Method;

@Aspect
@Component
@SuppressWarnings("all")
public class JmsValidateProceeder extends AbstractJmsProceeder {

    private final ValidationHelper validationHelper;

    @Autowired
    protected JmsValidateProceeder(JmsHelper jmsHelper, ValidationHelper validationHelper) {
        super(jmsHelper);
        this.validationHelper = validationHelper;
    }


    @Around("@annotation(org.ichop.commons.aop.JmsValidate)")
    public <R> Object validateModel(ProceedingJoinPoint joinPoint) throws Throwable {
        Class clazz = this.getModelClass(joinPoint);
        Message message = super.getMessage(joinPoint);

        R requestModel = (R) this.jmsHelper.toModel(message, clazz);

        if (!this.validationHelper.isValid(requestModel)) {
            super.jmsHelper.replyValidationError(message, requestModel);
            return null;
        }

        return joinPoint.proceed();
    }

    private Class getModelClass(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        JmsValidate jmsValidateAnnotation = method.getAnnotation(JmsValidate.class);

        return jmsValidateAnnotation.model();
    }

}

package ichop.threads.aop;

import ichop.threads.domain.models.jms.BaseRequestModel;
import ichop.threads.helpers.JmsHelper;
import ichop.threads.helpers.ValidationHelper;
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
public class ValidateModelProceeder {

    private final ValidationHelper validationHelper;
    private final JmsHelper jmsHelper;

    @Autowired
    public ValidateModelProceeder(ValidationHelper validationHelper, JmsHelper jmsHelper) {
        this.validationHelper = validationHelper;
        this.jmsHelper = jmsHelper;
    }

    @Around("@annotation(ichop.threads.aop.ValidateModel)")
    public <R extends BaseRequestModel> Object validateModel(ProceedingJoinPoint joinPoint) throws Throwable {
        Class clazz = this.getModelClass(joinPoint);
        Message message = (Message) joinPoint.getArgs()[0];

        R requestModel = (R) this.jmsHelper.getResultModel(message, clazz);

        if (!this.validationHelper.isValid(requestModel)) {
            this.jmsHelper.replyValidationError(message, requestModel);
            return null;
        }

        return joinPoint.proceed();
    }

    private Class<? extends BaseRequestModel> getModelClass(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        ValidateModel validateModelAnnotation = method.getAnnotation(ValidateModel.class);

        return validateModelAnnotation.model();
    }

}

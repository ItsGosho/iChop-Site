package ichop.threads.aop;

import ichop.threads.domain.models.jms.BaseRequestModel;
import ichop.threads.domain.models.jms.retrieve.ThreadGetByIdRequestModel;
import ichop.threads.helpers.JmsHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class PassModelAroundProceeder {

    private final JmsHelper jmsHelper;

    @Autowired
    public PassModelAroundProceeder(JmsHelper jmsHelper) {
        this.jmsHelper = jmsHelper;
    }

    @Around("@annotation(ichop.threads.aop.PassModel)")
    public Object skipOnNull(ProceedingJoinPoint joinPoint) throws Throwable {
        Class clazz = this.getModelClass(joinPoint);

       /* ThreadGetByIdRequestModel requestModel = this.jmsHelper.getResultModel(message, ThreadGetByIdRequestModel.class);

        if (!this.validationHelper.isValid(requestModel)) {
            this.jmsHelper.replyValidationError(message, requestModel);
            return;
        }
*/
        return joinPoint.proceed();
    }

    private Class<? extends BaseRequestModel> getModelClass(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        PassModel passModelAnnotation = method.getAnnotation(PassModel.class);

        return passModelAnnotation.model();
    }

}

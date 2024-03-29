package org.ichop.commons.aop;


import org.ichop.commons.domain.ReplyCandidate;
import org.ichop.commons.helpers.JmsHelper;
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
public class JmsAfterReturnProceeder extends AbstractJmsProceeder {

    @Autowired
    protected JmsAfterReturnProceeder(JmsHelper jmsHelper) {
        super(jmsHelper);
    }

    @Around(value = "@annotation(org.ichop.commons.aop.JmsAfterReturn)")
    public void afterReturn(ProceedingJoinPoint joinPoint) throws Throwable {
        Message message = super.getMessage(joinPoint);
        String msg = this.getMsg(joinPoint);

        Object object =  joinPoint.proceed();

        if (object != null) {
            super.jmsHelper.replySuccessful(message, object, msg);
        }
    }

    private String getMsg(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        JmsAfterReturn jmsAfterReturnAnnotation = method.getAnnotation(JmsAfterReturn.class);

        return jmsAfterReturnAnnotation.message();
    }

}

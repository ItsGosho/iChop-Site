package com.ichop.core.areas.jms.validations;

import com.ichop.core.areas.jms.exception.JmsServerIsDownException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class IsServerDownAfterReturningProceeder {

    @Pointcut(value = "execution(public * *(..))")
    public void anyPublicMethod() {
    }

    @AfterReturning(value = "anyPublicMethod() && @annotation(com.ichop.core.areas.jms.validations.IsServerDown)",
            returning = "result")
    public Object isServerDown(JoinPoint joinPoint, Object result) throws Throwable {

        if(result == null){
            throw new JmsServerIsDownException();
        }

        return result;
    }

}

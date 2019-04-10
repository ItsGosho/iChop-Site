package com.ichop.core.validators.method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class SkipOnNullAroundProceeder {

    @Around("@annotation(com.ichop.core.validators.method.SkipOnNull)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] methodPassedValues = joinPoint.getArgs();

        if(Arrays.asList(methodPassedValues).contains(null)){
            return null;
        }

        return joinPoint.proceed();
    }

}

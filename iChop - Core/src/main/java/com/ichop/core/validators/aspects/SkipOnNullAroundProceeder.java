package com.ichop.core.validators.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class SkipOnNullAroundProceeder {

    @Around("@annotation(com.ichop.core.validators.aspects.SkipOnNull)")
    public Object skipOnNull(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] methodPassedValues = joinPoint.getArgs();

        if(Arrays.asList(methodPassedValues).contains(null)){
            return null;
        }

        return joinPoint.proceed();
    }

}

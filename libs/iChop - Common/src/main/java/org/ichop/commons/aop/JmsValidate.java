package org.ichop.commons.aop;

import org.ichop.commons.domain.RequestCandidate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JmsValidate {

    Class<? extends RequestCandidate> model();

}

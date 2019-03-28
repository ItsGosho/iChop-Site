package com.ichop.core.web.interceptors;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Viewable {

    String name();

}

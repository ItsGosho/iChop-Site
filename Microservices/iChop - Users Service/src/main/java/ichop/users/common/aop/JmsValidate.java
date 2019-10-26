package ichop.users.common.aop;

import ichop.users.common.domain.BaseRequestModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JmsValidate {

    Class<? extends BaseRequestModel> model();

}

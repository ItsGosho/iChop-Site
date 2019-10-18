package ichop.common.jms.aop;

import ichop.common.jms.models.BaseRequestModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JmsValidate {

    Class<? extends BaseRequestModel> model();

}

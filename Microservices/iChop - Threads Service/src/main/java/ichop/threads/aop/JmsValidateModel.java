package ichop.threads.aop;

import ichop.threads.domain.models.jms.BaseRequestModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JmsValidateModel {

    Class<? extends BaseRequestModel> model();

}

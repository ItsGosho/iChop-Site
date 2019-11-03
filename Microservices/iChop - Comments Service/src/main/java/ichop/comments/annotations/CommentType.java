package ichop.comments.annotations;

import ichop.comments.domain.enums.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Scope("prototype")
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CommentType {

    Type type();

}

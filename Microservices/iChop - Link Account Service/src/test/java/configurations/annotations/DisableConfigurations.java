package configurations.annotations;

import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.*;

import static configurations.constants.ConditionalsConditionConstants.DISABLE_ARTEMIS_CONFIGURATION;

@SpringBootTest(properties = {
        DISABLE_ARTEMIS_CONFIGURATION
})
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DisableConfigurations {
}
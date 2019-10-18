package ichop.threads.aop;

import ichop.threads.domain.models.jms.BaseReplyModel;
import ichop.threads.domain.models.jms.BaseRequestModel;
import ichop.threads.domain.models.service.BaseServiceModel;
import ichop.threads.helpers.JmsHelper;
import ichop.threads.helpers.ValidationHelper;
import org.apache.activemq.artemis.jms.client.ActiveMQMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.lang.reflect.Method;

@Aspect
@Component
@SuppressWarnings("all")
public class JmsAfterReturnProceeder extends AbstractJmsProceeder {

    @Autowired
    protected JmsAfterReturnProceeder(JmsHelper jmsHelper) {
        super(jmsHelper);
    }

    @Around(value = "@annotation(ichop.threads.aop.JmsAfterReturn)")
    public <S extends BaseReplyModel> void test(ProceedingJoinPoint joinPoint) throws Throwable {
        Message message = super.getMessage(joinPoint);

        S object = (S) joinPoint.proceed();
        super.jmsHelper.replySuccessful(message, object);
    }

}

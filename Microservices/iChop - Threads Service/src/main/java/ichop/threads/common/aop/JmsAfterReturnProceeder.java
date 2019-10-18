package ichop.threads.common.aop;

import ichop.threads.common.domain.BaseReplyModel;
import ichop.threads.common.helpers.JmsHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Aspect
@Component
@SuppressWarnings("all")
public class JmsAfterReturnProceeder extends AbstractJmsProceeder {

    @Autowired
    protected JmsAfterReturnProceeder(JmsHelper jmsHelper) {
        super(jmsHelper);
    }

    @Around(value = "@annotation(ichop.threads.common.aop.JmsAfterReturn)")
    public <S extends BaseReplyModel> void test(ProceedingJoinPoint joinPoint) throws Throwable {
        Message message = super.getMessage(joinPoint);

        S object = (S) joinPoint.proceed();
        super.jmsHelper.replySuccessful(message, object);
    }

}

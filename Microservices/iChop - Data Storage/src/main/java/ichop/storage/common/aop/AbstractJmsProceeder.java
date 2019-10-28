package ichop.storage.common.aop;

import ichop.storage.common.helpers.JmsHelper;
import org.apache.activemq.artemis.jms.client.ActiveMQMessage;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.jms.Message;

@SuppressWarnings("all")
public abstract class AbstractJmsProceeder {

    protected final JmsHelper jmsHelper;

    protected AbstractJmsProceeder(JmsHelper jmsHelper) {
        this.jmsHelper = jmsHelper;
    }

    protected Message getMessage(ProceedingJoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof ActiveMQMessage) {
                return (Message) arg;
            }
        }

        return null;
    }

}

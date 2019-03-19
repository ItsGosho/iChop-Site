package itsgosho.listeners;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSListeners {



    @JmsListener(destination = "test-123")
    public void test(Integer integer){
        System.out.println(integer);
    }

}

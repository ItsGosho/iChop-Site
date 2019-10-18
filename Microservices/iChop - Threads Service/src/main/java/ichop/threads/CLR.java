package ichop.threads;

import ichop.threads.domain.models.jms.create.ThreadCreateReplyModel;
import ichop.threads.domain.models.jms.create.ThreadCreateRequestModel;
import ichop.threads.common.helpers.JmsHelper;
import ichop.threads.listener.ThreadJmsListenerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLR implements CommandLineRunner {

    private final ThreadJmsListenerImp threadJmsListener;
    private final JmsHelper jmsHelper;

    @Autowired
    public CLR(ThreadJmsListenerImp threadJmsListener, JmsHelper jmsHelper) {
        this.threadJmsListener = threadJmsListener;
        this.jmsHelper = jmsHelper;
    }

    @Override
    public void run(String... args) throws Exception {
        ThreadCreateRequestModel requestModel = new ThreadCreateRequestModel();
        requestModel.setTitle("123");
        requestModel.setContent("<h1>test</h1>");
        requestModel.setUserId("123");

        ThreadCreateReplyModel result = this.jmsHelper.sendAndReceive("thread_services.create", requestModel, ThreadCreateReplyModel.class);
        System.out.println(result.getTitle());
        System.out.println(result.getViews());
    }
}

package ichop.comments.requesters;

import ichop.comments.domain.models.jms.ThreadFindByIdRequest;
import org.ichop.commons.domain.EmptyRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ThreadRequesterImp implements ThreadRequester {

    private final JmsHelper jmsHelper;


    private final String findByIdDestination;


    public ThreadRequesterImp(JmsHelper jmsHelper,
                              @Value("${artemis.queue.threads.find.by.id}") String findByIdDestination) {
        this.jmsHelper = jmsHelper;
        this.findByIdDestination = findByIdDestination;
    }


    @Override
    public JmsReplyModel findById(String id) {
        ThreadFindByIdRequest request = new ThreadFindByIdRequest(id);

        return this.jmsHelper.sendAndReceive(this.findByIdDestination, request);
    }
}

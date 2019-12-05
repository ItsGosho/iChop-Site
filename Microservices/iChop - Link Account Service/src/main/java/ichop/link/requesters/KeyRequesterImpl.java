package ichop.link.requesters;

import ichop.link.domain.models.jms.IsKeyValidJmsRequest;
import ichop.link.domain.models.jms.KeyRetrieveJmsRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeyRequesterImpl implements KeyRequester {

    private final JmsHelper jmsHelper;

    private final String isKeyValidDestination;
    private final String keyRetrieveDestination;

    @Autowired
    public KeyRequesterImpl(JmsHelper jmsHelper,
                            @Value("${artemis.queue.link_account.is.key.valid}") String isKeyValidDestination,
                            @Value("${artemis.queue.link_account.key.retrieve}") String keyRetrieveDestination) {
        this.jmsHelper = jmsHelper;

        this.isKeyValidDestination = isKeyValidDestination;
        this.keyRetrieveDestination = keyRetrieveDestination;
    }


    @Override
    public JmsReplyModel isKeyValid(String key) {
        IsKeyValidJmsRequest request = new IsKeyValidJmsRequest(key);

        return this.jmsHelper.sendAndReceive(this.isKeyValidDestination, request);
    }

    @Override
    public JmsReplyModel findKeyByKey(String key) {
        KeyRetrieveJmsRequest request = new KeyRetrieveJmsRequest(key);

        return this.jmsHelper.sendAndReceive(this.keyRetrieveDestination, request);
    }
}

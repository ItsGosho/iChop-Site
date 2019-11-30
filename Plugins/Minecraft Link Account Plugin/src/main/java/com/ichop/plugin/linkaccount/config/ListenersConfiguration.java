package com.ichop.plugin.linkaccount.config;

import com.ichop.plugin.linkaccount.listeners.IsKeyValidListener;
import com.ichop.plugin.linkaccount.listeners.LinkCreateListener;
import com.ichop.plugin.linkaccount.listeners.LinkRetrieveListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.jms.*;

import static com.ichop.plugin.linkaccount.constants.LogConstants.ACTIVEMQ_LISTENER_INITIALIZED;

public class ListenersConfiguration {

    private static final Logger LOG = LogManager.getLogger(ListenersConfiguration.class);

    private static final String BASE_QUEUE_PREFIX = "link_account_plugin";
    private static final String IS_KEY_VALID_QUEUE = BASE_QUEUE_PREFIX + ".is.key.valid";
    private static final String LINK_CREATE_QUEUE = BASE_QUEUE_PREFIX + ".link.create";
    private static final String LINK_RETRIEVE_QUEUE = BASE_QUEUE_PREFIX + ".link.retrieve";

    private final Session session;
    private final IsKeyValidListener isKeyValidListener;
    private final LinkCreateListener linkCreateListener;
    private final LinkRetrieveListener linkRetrieveListener;

    @Inject
    public ListenersConfiguration(Session session,
                                  IsKeyValidListener isKeyValidListener,
                                  LinkCreateListener linkCreateListener,
                                  LinkRetrieveListener linkRetrieveListener) {
        this.session = session;
        this.isKeyValidListener = isKeyValidListener;
        this.linkCreateListener = linkCreateListener;
        this.linkRetrieveListener = linkRetrieveListener;
    }

    public void configure() {
        try {
            this.initListener(IS_KEY_VALID_QUEUE, this.isKeyValidListener);
            this.initListener(LINK_CREATE_QUEUE, this.linkCreateListener);
            this.initListener(LINK_RETRIEVE_QUEUE, this.linkRetrieveListener);
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    public <M extends MessageListener> void initListener(String destination, M listener) throws JMSException {
        Queue queue = this.session.createQueue(destination);
        MessageConsumer consumer = this.session.createConsumer(queue);
        consumer.setMessageListener(listener);
        //<editor-fold desc="LOG">
           LOG.info(String.format(ACTIVEMQ_LISTENER_INITIALIZED,destination));
        //</editor-fold>
    }
}

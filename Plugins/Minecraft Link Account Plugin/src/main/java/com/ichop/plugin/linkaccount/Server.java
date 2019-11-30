package com.ichop.plugin.linkaccount;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;

public class Server implements MessageListener {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String USERNAME = "123";
    private static final String PASSWORD = "123";

    private static int ackMode;
    private static String messageQueueName;
    private static String messageBrokerUrl;

    private Session session;
    private boolean transacted = false;
    private MessageProducer replyProducer;

    static {
        messageBrokerUrl = "tcp://localhost:61616";
        messageQueueName = "proba";
        ackMode = Session.AUTO_ACKNOWLEDGE;
    }

    public Server() {
        this.setupMessageQueueConsumer();
    }

    private void setupMessageQueueConsumer() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL, USERNAME, PASSWORD);
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            this.session = connection.createSession(this.transacted, ackMode);
            Destination adminQueue = this.session.createQueue(messageQueueName);

            //Setup a message producer to respond to messages from clients, we will get the destination
            //to send to from the JMSReplyTo header field from a Message
            this.replyProducer = this.session.createProducer(null);
            this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            //Set up a consumer to consume messages off of the admin queue
            MessageConsumer consumer = this.session.createConsumer(adminQueue);
            consumer.setMessageListener(this);
        } catch (JMSException e) {
            //Handle the exception appropriately
            System.out.println();
        }
    }

    public void onMessage(Message message) {
        System.out.println();
    }
}
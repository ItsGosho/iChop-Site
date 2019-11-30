package com.ichop.plugin.linkaccount;

import com.google.inject.Injector;
import com.ichop.plugin.linkaccount.config.ArtemisConfiguration;
import com.ichop.plugin.linkaccount.config.BeansConfiguration;
import com.ichop.plugin.linkaccount.loaders.CommandLoader;

import javax.inject.Inject;
import javax.jms.*;

public class CLR {

    public static final String IS_ACCOUNT_LINKED_URL = "http://http://localhost:8000/player/is-account-linked?uuid={uuid}";

    @Inject
    private CommandLoader commandLoader;


    public static void main(String[] args) throws JMSException, InterruptedException {
//        String url = IS_ACCOUNT_LINKED_URL.replace("{playerUUID}","8ed20904-3262-401a-901a-1946504d2eea");
//        try {
//            HttpResponse<JsonNode> jsonResponse = Unirest.get(url)
//                    .header("accept","application/json")
//                    .asJson();
//
//            boolean isAccountLinked = (boolean) jsonResponse.getBody().getObject().get("isAccountLinked");
//
//            System.out.println(isAccountLinked);
//
//        } catch (UnirestException e) {
//            e.printStackTrace();
//        }

       /* LinkAccount linkAccount = new LinkAccount();
        linkAccount.onEnable();*/

    /*    ArtemisConfiguration artemisConfiguration = new ArtemisConfiguration();
        Session session = artemisConfiguration.session();

        Destination destination = session.createQueue("proba");
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new TestListener());

        session.setMessageListener(new TestListener());*/

    }
}

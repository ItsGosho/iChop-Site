package com.ichop.linkaccount;

import com.ichop.linkaccount.services.KeyServices;
import com.ichop.linkaccount.services.PlayerLinkServices;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class CLR implements CommandLineRunner {

    private final KeyServices keyServices;
    private final PlayerLinkServices playerLinkServices;

    @Autowired
    public CLR(KeyServices keyServices, PlayerLinkServices playerLinkServices) {
        this.keyServices = keyServices;
        this.playerLinkServices = playerLinkServices;
    }

    @Override
    public void run(String... args) throws Exception {

//        //return to PLAYER_CREATE_LINK_KEY
//
//        if(!this.keyServices.isKeyExpired(keyServiceModel.getKey())){
//            System.out.println("Key is expired!");
//            return;
//        }
//
//
//        PlayerLinkCreateBindingModel playerLinkCreateBindingModel = new PlayerLinkCreateBindingModel();
//        playerLinkCreateBindingModel.setName("ItsGosho");
//        playerLinkCreateBindingModel.setPlayerUUID("8231-fds34-...");
//        playerLinkCreateBindingModel.setSiteUserUsername("ItsGosho");
//
//        this.playerLinkServices.linkToSiteUser(playerLinkCreateBindingModel);
    }
}

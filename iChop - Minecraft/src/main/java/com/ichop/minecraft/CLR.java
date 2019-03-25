package com.ichop.minecraft;

import com.ichop.minecraft.linkaccount.services.KeyServices;
import com.ichop.minecraft.linkaccount.services.PlayerLinkServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

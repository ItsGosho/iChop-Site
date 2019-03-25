package com.ichop.minecraft.linkaccount.listeners;

import com.google.common.collect.Lists;
import com.ichop.minecraft.linkaccount.domain.models.binding.PlayerLinkCreateBindingModel;
import com.ichop.minecraft.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.minecraft.linkaccount.services.KeyServices;
import com.ichop.minecraft.linkaccount.services.PlayerLinkServices;
import org.aspectj.weaver.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.*;

@Component
public class JMSListeners {

    private final JmsTemplate jmsTemplate;
    private final PlayerLinkServices playerLinkServices;
    private final KeyServices keyServices;

    @Autowired
    public JMSListeners(JmsTemplate jmsTemplate, PlayerLinkServices playerLinkServices, KeyServices keyServices) {
        this.jmsTemplate = jmsTemplate;
        this.playerLinkServices = playerLinkServices;
        this.keyServices = keyServices;
    }


    @JmsListener(destination = "ichop_minecraft-link_player_account")
    public Message linkPlayerAccount(Message message) throws JMSException {
        HashMap<String,Object> resultValues = new HashMap<>();

        PlayerLinkCreateBindingModel playerLinkCreateBindingModel = new PlayerLinkCreateBindingModel();
        playerLinkCreateBindingModel.setPlayerName((String) message.getObjectProperty("playerName"));
        playerLinkCreateBindingModel.setPlayerUUID((String) message.getObjectProperty("playerUUID"));
        playerLinkCreateBindingModel.setSiteUserUsername((String) message.getObjectProperty("siteUserUsername"));

        boolean result = this.playerLinkServices.linkToSiteUser(playerLinkCreateBindingModel);

        resultValues.put("isSuccessful",result);
        return this.convertValuesIntoMessage(resultValues);
    }

    @JmsListener(destination = "ichop_minecraft-is_player_link_key_valid")
    public Message isPlayerKeyValid(Message message) throws JMSException {
        Map<String,Object> recievedValues = this.messageToHashMap(message);
        String key = (String) recievedValues.get("key");

        boolean isValid = this.keyServices.isKeyValid(key);

        HashMap<String,Object> resultValues = new HashMap<>();
        resultValues.put("isValid",isValid);
        return this.convertValuesIntoMessage(resultValues);
    }

    @JmsListener(destination = "ichop_minecraft-is_player_linked_account_by_site_user")
    public Message isPlayerLinkedAccountBySiteUser(Message message) throws JMSException {
        Map<String,Object> recievedValues = this.messageToHashMap(message);
        String siteUserUsername = (String) recievedValues.get("siteUserUsername");

        boolean isLinked = this.playerLinkServices.isAccountLinkedBySiteUserUsername(siteUserUsername);

        HashMap<String,Object> resultValues = new HashMap<>();
        resultValues.put("isLinked",isLinked);
        return this.convertValuesIntoMessage(resultValues);
    }

    @JmsListener(destination = "ichop_minecraft-is_player_linked_account_by_uuid")
    public Message isPlayerLinkedAccountByUUID(Message message) throws JMSException {
        Map<String,Object> recievedValues = this.messageToHashMap(message);
        String uuid = (String) recievedValues.get("uuid");

        boolean isLinked = this.playerLinkServices.isAccountLinkedByUUID(uuid);

        HashMap<String,Object> resultValues = new HashMap<>();
        resultValues.put("isLinked",isLinked);
        return this.convertValuesIntoMessage(resultValues);
    }

    @JmsListener(destination = "ichop_minecraft-get_player_data_by_key")
    public Message getPlayerDataByKey(Message message) throws JMSException {
        HashMap<String,Object> resultValues = new HashMap<>();
        Map<String,Object> recievedValues = this.messageToHashMap(message);
        String key = (String) recievedValues.get("key");

        KeyServiceModel keyServiceModel = this.keyServices.getByKey(key);

        if(keyServiceModel == null){
            resultValues.put("isSuccessful",false);
            return this.convertValuesIntoMessage(resultValues);
        }

        resultValues.put("isSuccessful",true);
        resultValues.put("uuid",keyServiceModel.getPlayerUUID());
        resultValues.put("name",keyServiceModel.getPlayerName());
        return this.convertValuesIntoMessage(resultValues);
    }

    private Map<String,Object> messageToHashMap(Message message){
        Map<String,Object> result = new HashMap<>();
        try {
            Enumeration keys = message.getPropertyNames();
            Iterator iterator = keys.asIterator();

            while (iterator.hasNext()){
                String key = (String) iterator.next();
                result.put(key,message.getObjectProperty(key));
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Message convertValuesIntoMessage(HashMap<String,Object> values){
        try {

            Session session = this.jmsTemplate.getConnectionFactory().createConnection().createSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            Message message = session.createMessage();

            for (Map.Entry<String, Object> item : values.entrySet()) {
                message.setObjectProperty(item.getKey(),item.getValue());
            }

            return message;

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }



}

package com.ichop.linkaccount.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.linkaccount.domain.models.binding.PlayerLinkCreateBindingModel;
import com.ichop.linkaccount.domain.models.jms.key.receive.GetPlayerDataByKeyJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.key.receive.GetPlayerDataBySiteUserJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.key.receive.IsPlayerLinkKeyValidJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.key.returnn.GetPlayerDataByKeyJMSReturnModel;
import com.ichop.linkaccount.domain.models.jms.key.returnn.GetPlayerDataBySiteUserJMSReturnModel;
import com.ichop.linkaccount.domain.models.jms.key.returnn.IsPlayerLinkKeyValidJMSReturnModel;
import com.ichop.linkaccount.domain.models.jms.link.receive.IsPlayerLinkedAccountBySiteUserJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.link.receive.IsPlayerLinkedAccountByUUIDJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.link.receive.LinkPlayerAccountJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.link.returnn.IsPlayerLinkedAccountBySiteUserJMSReturnModel;
import com.ichop.linkaccount.domain.models.jms.link.returnn.IsPlayerLinkedAccountByUUIDJMSReturnModel;
import com.ichop.linkaccount.domain.models.jms.link.returnn.LinkPlayerAccountJMSReturnModel;
import com.ichop.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.linkaccount.domain.models.service.PlayerLinkServiceModel;
import com.ichop.linkaccount.services.KeyServices;
import com.ichop.linkaccount.services.PlayerLinkServices;
import org.modelmapper.ModelMapper;
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
    private final ObjectMapper objectMapper;

    @Autowired
    public JMSListeners(JmsTemplate jmsTemplate, PlayerLinkServices playerLinkServices, KeyServices keyServices, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.playerLinkServices = playerLinkServices;
        this.keyServices = keyServices;
        this.objectMapper = objectMapper;
    }


    @JmsListener(destination = "ichop_link_account-link_player_account")
    public Message linkPlayerAccount(Message message) throws JMSException {
        HashMap<String, Object> resultValues = new HashMap<>();

        LinkPlayerAccountJMSReceiveModel receivedJMSModel =
                this.objectMapper.convertValue(message.getObjectProperty("jmsModel"), LinkPlayerAccountJMSReceiveModel.class);

        PlayerLinkCreateBindingModel bindingModel = this.objectMapper.convertValue(receivedJMSModel, PlayerLinkCreateBindingModel.class);

        boolean result = this.playerLinkServices.linkToSiteUser(bindingModel);

        LinkPlayerAccountJMSReturnModel resultModel = new LinkPlayerAccountJMSReturnModel();
        resultModel.setSuccessful(result);
        resultValues.put("resultModel",this.objectMapper.convertValue(resultModel,Map.class));

        return this.convertValuesIntoMessage(resultValues);
    }

    @JmsListener(destination = "ichop_link_account-is_player_link_key_valid")
    public Message isPlayerKeyValid(Message message) throws JMSException {
        HashMap<String, Object> resultValues = new HashMap<>();
        IsPlayerLinkKeyValidJMSReceiveModel receivedJMSModel =
                this.objectMapper.convertValue(message.getObjectProperty("jmsModel"),IsPlayerLinkKeyValidJMSReceiveModel.class);

        boolean isValid = this.keyServices.isKeyValid(receivedJMSModel.getKey());

        IsPlayerLinkKeyValidJMSReturnModel resultModel = new IsPlayerLinkKeyValidJMSReturnModel();
        resultModel.setValid(isValid);
        resultValues.put("resultModel", this.objectMapper.convertValue(resultModel,Map.class));

        return this.convertValuesIntoMessage(resultValues);
    }

    @JmsListener(destination = "ichop_link_account-is_player_linked_account_by_site_user")
    public Message isPlayerLinkedAccountBySiteUser(Message message) throws JMSException {
        HashMap<String, Object> resultValues = new HashMap<>();
        IsPlayerLinkedAccountBySiteUserJMSReceiveModel receivedJMSModel =
                this.objectMapper.convertValue(message.getObjectProperty("jmsModel"),IsPlayerLinkedAccountBySiteUserJMSReceiveModel.class);

        boolean isLinked = this.playerLinkServices.isAccountLinkedBySiteUserUsername(receivedJMSModel.getSiteUserUsername());

        IsPlayerLinkedAccountBySiteUserJMSReturnModel resultModel = new IsPlayerLinkedAccountBySiteUserJMSReturnModel();
        resultModel.setLinked(isLinked);
        resultValues.put("resultModel", this.objectMapper.convertValue(resultModel,Map.class));

        return this.convertValuesIntoMessage(resultValues);
    }

    @JmsListener(destination = "ichop_link_account-is_player_linked_account_by_uuid")
    public Message isPlayerLinkedAccountByUUID(Message message) throws JMSException {
        HashMap<String, Object> resultValues = new HashMap<>();
        IsPlayerLinkedAccountByUUIDJMSReceiveModel receivedJMSModel =
                this.objectMapper.convertValue(message.getObjectProperty("jmsModel"),IsPlayerLinkedAccountByUUIDJMSReceiveModel.class);

        boolean isLinked = this.playerLinkServices.isAccountLinkedByUUID(receivedJMSModel.getUuid());

        IsPlayerLinkedAccountByUUIDJMSReturnModel resultModel = new IsPlayerLinkedAccountByUUIDJMSReturnModel();
        resultModel.setLinked(isLinked);
        resultValues.put("resultModel", this.objectMapper.convertValue(resultModel,Map.class));

        return this.convertValuesIntoMessage(resultValues);
    }

    @JmsListener(destination = "ichop_link_account-get_player_data_by_key")
    public Message getPlayerDataByKey(Message message) throws JMSException {
        HashMap<String, Object> resultValues = new HashMap<>();
        GetPlayerDataByKeyJMSReceiveModel receivedJMSModel =
                this.objectMapper.convertValue(message.getObjectProperty("jmsModel"),GetPlayerDataByKeyJMSReceiveModel.class);

        KeyServiceModel keyServiceModel = this.keyServices.getByKey(receivedJMSModel.getKey());

        GetPlayerDataByKeyJMSReturnModel resultModel = this.objectMapper.convertValue(keyServiceModel,GetPlayerDataByKeyJMSReturnModel.class);

        resultValues.put("resultModel",this.objectMapper.convertValue(resultModel,Map.class));
        return this.convertValuesIntoMessage(resultValues);
    }

    @JmsListener(destination = "ichop_link_account-get_player_data_by_site_user")
    public Message getPlayerDataBySiteUser(Message message) throws JMSException {
        HashMap<String, Object> resultValues = new HashMap<>();
        GetPlayerDataBySiteUserJMSReceiveModel receivedJMSModel =
                this.objectMapper.convertValue(message.getObjectProperty("jmsModel"),GetPlayerDataBySiteUserJMSReceiveModel.class);

        PlayerLinkServiceModel playerLinkServiceModel = this.playerLinkServices.getBySiteUser(receivedJMSModel.getSiteUserUsername());

        GetPlayerDataBySiteUserJMSReturnModel resultModel = this.objectMapper.convertValue(playerLinkServiceModel,GetPlayerDataBySiteUserJMSReturnModel.class);
        resultValues.put("resultModel", this.objectMapper.convertValue(resultModel,Map.class));
        return this.convertValuesIntoMessage(resultValues);
    }

    private Map<String, Object> messageToHashMap(Message message) {
        Map<String, Object> result = new HashMap<>();
        try {
            Enumeration keys = message.getPropertyNames();
            Iterator iterator = keys.asIterator();

            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                result.put(key, message.getObjectProperty(key));
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Message convertValuesIntoMessage(HashMap<String, Object> values) {
        try {

            Session session = this.jmsTemplate.getConnectionFactory().createConnection().createSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            Message message = session.createMessage();

            for (Map.Entry<String, Object> item : values.entrySet()) {
                message.setObjectProperty(item.getKey(), item.getValue());
            }

            return message;

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }


}

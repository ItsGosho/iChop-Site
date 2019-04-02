package com.ichop.linkaccount.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.linkaccount.components.JmsServices;
import com.ichop.linkaccount.components.ValidationUtil;
import com.ichop.linkaccount.domain.models.binding.PlayerLinkCreateBindingModel;
import com.ichop.linkaccount.domain.models.jms.key.receive.GetPlayerDataBySiteUserJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.key.returnn.GetPlayerDataBySiteUserJMSReturnModel;
import com.ichop.linkaccount.domain.models.jms.playerlink.receive.GetPlayerDataByPlayerUUIDJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.playerlink.receive.IsPlayerLinkedAccountBySiteUserJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.playerlink.receive.IsPlayerLinkedAccountByUUIDJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.playerlink.receive.LinkPlayerAccountJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.playerlink.returnn.GetPlayerDataByPlayerUUIDJMSReturnModel;
import com.ichop.linkaccount.domain.models.jms.playerlink.returnn.IsPlayerLinkedAccountBySiteUserJMSReturnModel;
import com.ichop.linkaccount.domain.models.jms.playerlink.returnn.IsPlayerLinkedAccountByUUIDJMSReturnModel;
import com.ichop.linkaccount.domain.models.jms.playerlink.returnn.LinkPlayerAccountJMSReturnModel;
import com.ichop.linkaccount.domain.models.service.PlayerLinkServiceModel;
import com.ichop.linkaccount.listeners.annotations.playerlink.GetPlayerDataBySiteUserListener;
import com.ichop.linkaccount.listeners.annotations.playerlink.GetPlayerDataByUUIDListener;
import com.ichop.linkaccount.listeners.annotations.playerlink.IsPlayerLinkedAccountByUUIDListener;
import com.ichop.linkaccount.listeners.annotations.playerlink.LinkPlayerAccountListener;
import com.ichop.linkaccount.services.KeyServices;
import com.ichop.linkaccount.services.PlayerLinkServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class PlayerLinkJmsListeners extends BaseJmsListener {

    private final PlayerLinkServices playerLinkServices;
    private final KeyServices keyServices;

    @Autowired
    public PlayerLinkJmsListeners(JmsServices jmsServices, ObjectMapper objectMapper, ValidationUtil validationUtil, PlayerLinkServices playerLinkServices, KeyServices keyServices) {
        super(jmsServices, objectMapper, validationUtil);
        this.playerLinkServices = playerLinkServices;
        this.keyServices = keyServices;
    }


    @LinkPlayerAccountListener
    public Message linkPlayerAccount(Message message) throws JMSException {
        LinkPlayerAccountJMSReceiveModel receivedJMSModel = super.jmsServices.getJmsModel(message,LinkPlayerAccountJMSReceiveModel.class);

        if(super.validationUtil.validate(receivedJMSModel).hasErrors()){
            return super.jmsServices.returnErrorModel(receivedJMSModel,LinkPlayerAccountJMSReturnModel.class);
        }

        PlayerLinkCreateBindingModel bindingModel = this.objectMapper.convertValue(receivedJMSModel, PlayerLinkCreateBindingModel.class);
        bindingModel.setPlayerUUID(this.keyServices.getByKey(receivedJMSModel.getKey()).getPlayerUUID());
        bindingModel.setPlayerName(this.keyServices.getByKey(receivedJMSModel.getKey()).getPlayerName());
        boolean result = this.playerLinkServices.linkToSiteUser(bindingModel);

        LinkPlayerAccountJMSReturnModel resultModel = new LinkPlayerAccountJMSReturnModel();
        resultModel.setSuccessful(result);

        return super.jmsServices.returnResultModel(resultModel);
    }

    @IsPlayerLinkedAccountByUUIDListener
    public Message isPlayerLinkedAccountByUUID(Message message) throws JMSException {
        IsPlayerLinkedAccountByUUIDJMSReceiveModel receivedJMSModel = super.jmsServices.getJmsModel(message,IsPlayerLinkedAccountByUUIDJMSReceiveModel.class);

        if(super.validationUtil.validate(receivedJMSModel).hasErrors()){
            return super.jmsServices.returnErrorModel(receivedJMSModel,IsPlayerLinkedAccountByUUIDJMSReturnModel.class);
        }

        boolean isLinked = this.playerLinkServices.isAccountLinkedByUUID(receivedJMSModel.getUuid());
        IsPlayerLinkedAccountByUUIDJMSReturnModel resultModel = new IsPlayerLinkedAccountByUUIDJMSReturnModel();
        resultModel.setLinked(isLinked);

        return this.jmsServices.returnResultModel(resultModel);
    }

    @GetPlayerDataBySiteUserListener
    public Message getPlayerDataBySiteUser(Message message) throws JMSException {
        GetPlayerDataBySiteUserJMSReceiveModel receivedJMSModel = super.jmsServices.getJmsModel(message,GetPlayerDataBySiteUserJMSReceiveModel.class);

        if(super.validationUtil.validate(receivedJMSModel).hasErrors()){
            return super.jmsServices.returnErrorModel(receivedJMSModel,GetPlayerDataBySiteUserJMSReturnModel.class);
        }

        PlayerLinkServiceModel playerLinkServiceModel = this.playerLinkServices.getBySiteUser(receivedJMSModel.getSiteUserUsername());
        GetPlayerDataBySiteUserJMSReturnModel resultModel = this.objectMapper.convertValue(playerLinkServiceModel,GetPlayerDataBySiteUserJMSReturnModel.class);
        return this.jmsServices.returnResultModel(resultModel);
    }


    @GetPlayerDataByUUIDListener
    public Message getPlayerDataByUuid(Message message) throws JMSException {
        GetPlayerDataByPlayerUUIDJMSReceiveModel receivedJMSModel = super.jmsServices.getJmsModel(message,GetPlayerDataByPlayerUUIDJMSReceiveModel.class);

        if(super.validationUtil.validate(receivedJMSModel).hasErrors()){
            return super.jmsServices.returnErrorModel(receivedJMSModel,GetPlayerDataByPlayerUUIDJMSReturnModel.class);
        }

        PlayerLinkServiceModel playerLinkServiceModel = this.playerLinkServices.getByUUID(receivedJMSModel.getUuid());
        GetPlayerDataByPlayerUUIDJMSReturnModel resultModel = this.objectMapper.convertValue(playerLinkServiceModel,GetPlayerDataByPlayerUUIDJMSReturnModel.class);
        return this.jmsServices.returnResultModel(resultModel);
    }


}

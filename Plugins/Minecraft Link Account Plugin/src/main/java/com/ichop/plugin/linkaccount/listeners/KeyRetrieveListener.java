package com.ichop.plugin.linkaccount.listeners;

import com.ichop.plugin.linkaccount.commons.helpers.JmsHelper;
import com.ichop.plugin.linkaccount.domain.models.jms.KeyRetrieveJmsRequest;
import com.ichop.plugin.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.plugin.linkaccount.services.KeyServices;

import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

public class KeyRetrieveListener implements MessageListener {

    private final JmsHelper jmsHelper;
    private final KeyServices keyServices;

    @Inject
    public KeyRetrieveListener(JmsHelper jmsHelper, KeyServices keyServices) {
        this.jmsHelper = jmsHelper;
        this.keyServices = keyServices;
    }

    @Override
    public void onMessage(Message message) {
        KeyRetrieveJmsRequest request = this.jmsHelper.toModel(message, KeyRetrieveJmsRequest.class);

        KeyServiceModel key = this.keyServices.findByLinkKey(request.getLinkKey());

        if (key == null) {
            this.jmsHelper.replyValidationError(message, "Key not found!");
            return;
        }

        this.jmsHelper.replySuccessful(message, key, "Fetch successful!");
    }

}
